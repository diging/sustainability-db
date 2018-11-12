package edu.asu.diging.sustainability.core.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import edu.asu.diging.sustainability.core.data.AnnotationRepository;
import edu.asu.diging.sustainability.core.data.ResourceRepository;
import edu.asu.diging.sustainability.core.exception.NotAValidResourceException;
import edu.asu.diging.sustainability.core.model.IResource;
import edu.asu.diging.sustainability.core.model.impl.Resource;
import edu.asu.diging.sustainability.core.service.IAmphoraConnector;
import edu.asu.diging.sustainability.core.service.IResourceManager;

@Service
@PropertySource("classpath:/config.properties")
public class ResourceManager implements IResourceManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${sustainability_uri_prefix}")
    private String uriPrefix;

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private AnnotationRepository annotationRepo;

    @Autowired
    private IAmphoraConnector connector;

    private CacheManager cacheManager;
    private Cache<String, IResource> proxyCache;

    @PostConstruct
    public void init() {

        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        proxyCache = cacheManager.createCache("proxies", CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, IResource.class, ResourcePoolsBuilder.heap(20)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.asu.diging.sustainability.core.service.impl.IResourceManager#getResource(
     * java.lang.String)
     */
    @Override
    public IResource getResource(String uri) throws NotAValidResourceException {
        if (proxyCache.containsKey(uri)) {
            return proxyCache.get(uri);
        }

        Optional<Resource> optional = resourceRepo.findById(uri);
        if (optional.isPresent()) {
            return optional.get();
        }
        
        checkUri(uri);

        ResourceProxy proxy = new ResourceProxy(uri);
        proxyCache.put(uri, proxy);
        
        ListenableFuture<IResource> result = connector.getResource(uri);
        result.addCallback(new ResourceCallback(proxy));

        return new ResourceProxy(uri);
    }

    @Override
    public List<IResource> findAll() {
        List<IResource> resources = new ArrayList<>();
        Set<String> uris = new HashSet<>();
        resourceRepo.findAll().forEach(r -> {
            resources.add(r);
            uris.add(r.getUri());
        });

        List<String> allUris = annotationRepo.findAllUris();
        allUris.forEach(u -> {
            if (!uris.contains(u)) {
                IResource res = new ResourceProxy(u);
                resources.add(res);
            }
        });
        return resources;
    }

    @Override
    public void updateResource(String uri) throws NotAValidResourceException {
        IResource proxy = null;
        if (proxyCache.containsKey(uri)) {
            proxy = proxyCache.get(uri);
        }

        checkUri(uri);

        ListenableFuture<IResource> result = connector.getResource(uri);
        result.addCallback(new ResourceCallback((ResourceProxy) proxy));
    }

    private void checkUri(String uri) throws NotAValidResourceException {
        // let's not bother with URIs that don't have the default Prefix
        if (!uri.startsWith(uriPrefix)) {
            throw new NotAValidResourceException("Unknown URI prefix: " + uri);
        }
    }

    @Override
    public void updateAll() {
        List<IResource> all = findAll();
        all.forEach(r -> {
            try {
                updateResource(r.getUri());
            } catch (NotAValidResourceException e) {
                logger.warn("Resourec with uri " + r.getUri() + " could not be updated.", e);
            }
        });
    }

    class ResourceCallback implements ListenableFutureCallback<IResource> {

        private ResourceProxy proxy;

        public ResourceCallback() {
        }

        public ResourceCallback(ResourceProxy proxy) {
            this.proxy = proxy;
        }

        @Override
        public void onSuccess(IResource resource) {
            resource.setUpdatedOn(ZonedDateTime.now());
            resourceRepo.save((Resource) resource);
            if (this.proxy != null) {
                this.proxy.setProxiedResource(resource);
            }
        }

        @Override
        public void onFailure(Throwable arg0) {
            // for now do nothing
        }

    }
}
