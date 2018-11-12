package edu.asu.diging.sustainability.core.service;

import java.util.List;

import edu.asu.diging.sustainability.core.model.IResource;

public interface IResourceManager {

    IResource getResource(String uri);

    List<IResource> findAll();

    void updateAll();

}