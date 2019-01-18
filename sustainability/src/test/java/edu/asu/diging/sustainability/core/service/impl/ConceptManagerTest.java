package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.SearchCategory;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class ConceptManagerTest {

    @Mock
    private ConceptRepository conceptRepo;

    @InjectMocks
    private ConceptManager conceptManager = new ConceptManager();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_storeConceptSearchCategoriesAndAlias_success() {
        Concept concept = new Concept();
        concept.setId("ConceptId1");
        String alias = "concept";
        List<SearchCategory> categoryList = new ArrayList<SearchCategory>();
        concept.setSearchCategories(categoryList);
        concept.setAlias(alias);
        Mockito.when(conceptRepo.findById("ConceptId1")).thenReturn(Optional.of(concept));
        Mockito.when(conceptRepo.save(concept)).thenReturn(concept);
        IConcept savedConcept = conceptManager.storeConceptSearchCategoriesAndAlias(concept.getId(),
                categoryList, alias);
        verify(conceptRepo).save(concept);
        assertEquals(savedConcept.getAlias(), concept.getAlias());
        assertEquals(savedConcept.getSearchCategories(), concept.getSearchCategories());
    }

}
