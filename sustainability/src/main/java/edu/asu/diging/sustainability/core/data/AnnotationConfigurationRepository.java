package edu.asu.diging.sustainability.core.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.asu.diging.sustainability.core.model.config.impl.AnnotationConfiguration;

public interface AnnotationConfigurationRepository extends PagingAndSortingRepository<AnnotationConfiguration, String> {

}
