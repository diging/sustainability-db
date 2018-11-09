package edu.asu.diging.sustainability.core.service;

import java.util.Collection;
import java.util.List;

import edu.asu.diging.sustainability.core.model.config.IAnnotationConfiguration;
import edu.asu.diging.sustainability.web.admin.pages.AnnotationConfig;

public interface IAnnotationConfigurationManager {

    List<IAnnotationConfiguration> findAll();

    void storeConfigurations(Collection<AnnotationConfig> configs);

    IAnnotationConfiguration getConfiguration(String conceptId);

}