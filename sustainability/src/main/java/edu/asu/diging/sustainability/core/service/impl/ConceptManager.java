package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.SearchCategory;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import edu.asu.diging.sustainability.core.service.IConceptManager;

@Service
@Transactional
public class ConceptManager implements IConceptManager {

    @Autowired
    private ConceptRepository conceptRepo;

    /*
     * (non-Javadoc)
     * 
     * @see edu.asu.diging.sustainability.core.service.impl.IConceptManager#getConcepts()
     */
    @Override
    public List<IConcept> getConcepts() {
        Iterable<Concept> concepts = conceptRepo.findAll();
        List<IConcept> allConcepts = new ArrayList<>();
        for (Concept c : concepts) {
            // load children
            c.getChildren().size();
            allConcepts.add(c);
        }
        return allConcepts;
    }

    @Override
    public List<IConcept> getTopConcepts() {
        Iterable<Concept> concepts = conceptRepo.findByParentIsNull();
        List<IConcept> allConcepts = new ArrayList<>();
        for (Concept c : concepts) {
            // load children
            c.getChildren().size();
            allConcepts.add(c);
        }
        return allConcepts;
    }

    @Override
    public IConcept getConceptById(String id) {
        return (conceptRepo.findById(id)).get();
    }

    /**
     * Store the given search categories with the provided concept.
     * 
     * @param conceptId The id of the concept for which the search categories are provided.
     * @param searchCategories a list of roles.
     */
    @Override
    public void storeConceptSearchCategories(String conceptId, List<SearchCategory> searchCategories) {
        IConcept concept = getConceptById(conceptId);
        concept.setSearchCategories(searchCategories);
        conceptRepo.save((Concept) concept);
    }

    /**
     * Store the alias for concept name for the concept with given Id.
     * 
     * @param conceptId The id of the concept for which the alias is provided.
     * @param alias Alias for the concept name to be saved.
     */
    @Override
    public void storeConceptAlias(String conceptId, String alias) {
        IConcept concept = getConceptById(conceptId);
        concept.setAlias(alias);
        conceptRepo.save((Concept) concept);
        
    }
}
