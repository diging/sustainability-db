package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Concept;

public class ConceptManagerTest {

    @Mock
    private ConceptRepository conceptRepo;
    
    @InjectMocks
    private ConceptManager conceptManager = new ConceptManager();
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    public void test_storeConceptSearchCategoriesAndAlias_success() {
        Concept concept = new Concept();
        conceptRepo.save(concept);
        IConcept mockConcept = conceptManager.getConceptById(concept.getId());
        mockConcept.setSearchCategories(new ArrayList<>());
        mockConcept.setAlias(Mockito.anyString());
        Mockito.when(conceptRepo.save((Concept)mockConcept)).thenReturn((Concept)mockConcept);
    }
}
