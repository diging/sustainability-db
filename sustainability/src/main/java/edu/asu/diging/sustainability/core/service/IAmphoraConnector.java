package edu.asu.diging.sustainability.core.service;

import org.springframework.util.concurrent.ListenableFuture;

import edu.asu.diging.sustainability.core.model.IResource;

public interface IAmphoraConnector {

    ListenableFuture<IResource> getResource(String uri);

}