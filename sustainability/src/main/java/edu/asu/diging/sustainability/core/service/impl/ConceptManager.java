package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.model.IConcept;
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

    @Override
    public void updateConceptRoles(IConcept concept, String[] roles) {
        for (String role : roles) {
            concept.addRole(role);
        }
        concept = conceptRepo.save((Concept) concept);
        return;
    }

    @Override
    public void resetRoles() {
        Iterable<Concept> concepts = conceptRepo.findAll();
        for (Concept c : concepts) {
            c.setRoles(new ArrayList<String>());
        }
        return;
    }
}
