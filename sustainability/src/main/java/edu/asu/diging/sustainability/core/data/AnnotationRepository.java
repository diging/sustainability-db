package edu.asu.diging.sustainability.core.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.asu.diging.sustainability.core.model.impl.Annotation;

@Repository
public interface AnnotationRepository extends PagingAndSortingRepository<Annotation, String> {

}
