package edu.asu.diging.sustainability.core.service;

import java.util.List;

import edu.asu.diging.sustainability.core.model.IConcept;

public interface IConceptManager {

    List<IConcept> getConcepts();

    List<IConcept> getTopConcepts();

    IConcept getConceptById(String id);

    void updateConceptRoles(IConcept concept, String[] roles);

    void resetRoles();

}