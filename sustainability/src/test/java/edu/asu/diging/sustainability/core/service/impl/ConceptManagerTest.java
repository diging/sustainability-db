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
import edu.asu.diging.sustainability.core.model.SearchCategory;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import static org.mockito.Mockito.verify;

public class ConceptManagerTest {

    private String alias = "concept";
    private List<SearchCategory> categoryList = new ArrayList<SearchCategory>();
    @Mock
    private ConceptRepository conceptRepo;

    @InjectMocks
    private ConceptManager conceptManager = new ConceptManager();

    private Concept concept = new Concept();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        concept.setId("ConceptId1");
    }

    @Test
    public void test_storeConceptSearchCategoriesAndAlias_success() {
        concept.setSearchCategories(categoryList);
        concept.setAlias(alias);
        Mockito.when(conceptRepo.findById("ConceptId1")).thenReturn(Optional.of(concept));
        Mockito.when(conceptRepo.save(concept)).thenReturn(concept);
        conceptManager.storeConceptSearchCategoriesAndAlias(concept.getId(), categoryList, alias);
        verify(conceptRepo).save(concept);
    }

}
