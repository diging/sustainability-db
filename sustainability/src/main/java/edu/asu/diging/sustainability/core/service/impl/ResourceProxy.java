package edu.asu.diging.sustainability.core.service.impl;

import java.time.ZonedDateTime;
import java.util.List;

import edu.asu.diging.sustainability.core.model.IMetadatum;
import edu.asu.diging.sustainability.core.model.IResource;

public class ResourceProxy implements IResource {
    
    private IResource proxiedResource;
    private String uri;
    
    public ResourceProxy(String uri) {
        this.uri = uri;
    }

    @Override
    public String getUri() {
        if (proxiedResource != null) {
            return proxiedResource.getUri();
        }
        return uri;
    }

    @Override
    public void setUri(String uri) {
        throw new UnsupportedOperationException("Setting values throught a proxy is not yet supported.");
    }

    @Override
    public List<IMetadatum> getMetadata() {
        if (proxiedResource != null) {
            return proxiedResource.getMetadata();
        }
        return null;
    }

    @Override
    public void setMetadata(List<IMetadatum> metadata) {
        throw new UnsupportedOperationException("Setting values throught a proxy is not yet supported.");
    }

    @Override
    public ZonedDateTime getUpdatedOn() {
        if (proxiedResource != null) {
            return proxiedResource.getUpdatedOn();
        }
        return null;
    }

    @Override
    public void setUpdatedOn(ZonedDateTime updatedOn) {
        throw new UnsupportedOperationException("Setting values throught a proxy is not yet supported.");
    }

    public IResource getProxiedResource() {
        return proxiedResource;
    }

    public void setProxiedResource(IResource proxiedResource) {
        this.proxiedResource = proxiedResource;
    }

    @Override
    public String getTitle() {
        if (proxiedResource != null) {
            return proxiedResource.getTitle();
        }
        return this.uri;
    }

    @Override
    public String getYear() {
        if (proxiedResource != null) {
            return proxiedResource.getYear();
        }
        return null;
    }

    @Override
    public List<IMetadatum> getOthers() {
        if (proxiedResource != null) {
            return proxiedResource.getOthers();
        }
        return null;
    }

}
