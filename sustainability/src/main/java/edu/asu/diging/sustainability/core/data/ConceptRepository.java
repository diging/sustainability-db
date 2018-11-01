package edu.asu.diging.sustainability.core.data;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import edu.asu.diging.sustainability.core.model.impl.Concept;

@Repository
public interface ConceptRepository extends PagingAndSortingRepository<Concept, String> {

    List<Concept> findByUri(String uri);

    List<Concept> findByName(String name);

    List<Concept> findByParentIsNull();

}