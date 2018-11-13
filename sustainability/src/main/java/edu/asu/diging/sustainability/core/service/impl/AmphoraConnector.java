package edu.asu.diging.sustainability.core.service.impl;

import java.net.URI;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import edu.asu.diging.sustainability.core.model.IResource;
import edu.asu.diging.sustainability.core.model.impl.Metadatum;
import edu.asu.diging.sustainability.core.model.impl.Resource;
import edu.asu.diging.sustainability.core.service.IAmphoraConnector;
import edu.asu.diging.sustainability.core.service.impl.amphora.AmphoraRelation;
import edu.asu.diging.sustainability.core.service.impl.amphora.AmphoraResponse;
import reactor.core.publisher.Mono;

@Service
@PropertySource("classpath:/config.properties")
public class AmphoraConnector implements IAmphoraConnector {

    @Value("${amphora_url}")
    private String amphoraUrl;

    @Value("${amphora_token}")
    private String amphoraToken;

    @Value("${amphora_resource_endpoint}")
    private String amphoraEndpoint;

    private WebClient client;

    @PostConstruct
    public void init() {
        client = WebClient.builder().baseUrl(amphoraUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Token " + amphoraToken).filter(new FollowRedirects()).build();
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IAmphoraConnector#getResource(java.lang.String)
     */
    @Override
    @Async
    public ListenableFuture<IResource> getResource(String uri) {
        Mono<AmphoraResponse> response = client.get().uri(amphoraEndpoint, uri).retrieve().bodyToMono(AmphoraResponse.class);
        AmphoraResponse amphoraResponse = response.block();     
        IResource resource = createResource(uri, amphoraResponse);
        return new AsyncResult<IResource>(resource);
    }

    private IResource createResource(String uri, AmphoraResponse amphoraResponse) {
        IResource resource = new Resource();
        resource.setUri(uri);
        resource.setMetadata(new ArrayList<>());
        for (AmphoraRelation relation : amphoraResponse.getRelations_from()) {
            if (relation.getTarget() != null && relation.getPredicate() != null) {
                Metadatum datum = new Metadatum();
                datum.setLabel(relation.getPredicate().getName());
                datum.setValue(relation.getTarget().getName());
                datum.setPredicate(relation.getPredicate().getUri());
                resource.getMetadata().add(datum);
            }
        }
        return resource;
    }
    
    class FollowRedirects implements ExchangeFilterFunction {
        @Override
        public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
            return next.exchange(request)
                .flatMap( response -> redirectIfNecessary(request, next, response));
        }

        /*
         * Modified from: 
         * https://github.com/spring-projects/spring-security/blob/master/
         * config/src/test/java/org/springframework/security/htmlunit/server/HtmlUnitWebTestClient.java
         */
        private Mono<ClientResponse> redirectIfNecessary(ClientRequest request, ExchangeFunction next, ClientResponse response) {
            if (response.statusCode().is3xxRedirection()) {
                String redirectUrl = response.headers().header("Location").get(0);
                String host = request.url().getHost();
                String scheme = request.url().getScheme();
                ClientRequest redirect = ClientRequest.create(HttpMethod.GET, URI.create(scheme + "://" + host + redirectUrl))
                        .headers(headers -> headers.addAll(request.headers())).build();
                return next.exchange(redirect).flatMap(r -> redirectIfNecessary(request, next, r));
            }

            return Mono.just(response);
        }
    }
}
