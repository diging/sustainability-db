package edu.asu.diging.sustainability.core.service;

import java.util.List;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.SearchCategory;

public interface IConceptManager {

    List<IConcept> getConcepts();

    List<IConcept> getTopConcepts();

    IConcept getConceptById(String id);

    void storeConceptSearchCategories(String conceptId, List<SearchCategory> roles);

    void storeConceptAlias(String conceptId, String alias);

}