package edu.asu.diging.sustainability.core.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.impl.Annotation;

@Repository
public interface AnnotationRepository extends PagingAndSortingRepository<Annotation, String> {

    @Query("SELECT a FROM Annotation a WHERE a.concept.id = :conceptId")
    public List<IAnnotation> findByConceptId(@Param("conceptId") String conceptId);
    
    @Query("SELECT a FROM Annotation a WHERE a.occursIn = :uri")
    public List<IAnnotation> findByUri(@Param("uri")String uri);
    
    @Query("SELECT DISTINCT a.occursIn FROM Annotation a")
    public List<String> findAllUris();
}
