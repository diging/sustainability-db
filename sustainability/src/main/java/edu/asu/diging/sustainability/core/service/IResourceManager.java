package edu.asu.diging.sustainability.core.service;

import java.util.List;

import edu.asu.diging.sustainability.core.exception.NotAValidResourceException;
import edu.asu.diging.sustainability.core.model.IResource;

public interface IResourceManager {

    IResource getResource(String uri) throws NotAValidResourceException;

    List<IResource> findAll();

    void updateAll();

    void updateResource(String uri) throws NotAValidResourceException;

}