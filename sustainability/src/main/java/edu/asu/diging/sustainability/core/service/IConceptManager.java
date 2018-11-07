package edu.asu.diging.sustainability.core.service;

import java.util.List;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Roles;

public interface IConceptManager {

    List<IConcept> getConcepts();

    List<IConcept> getTopConcepts();

    IConcept getConceptById(String id);

    void configureConceptRoles(String conceptId, List<Roles> roles);

}