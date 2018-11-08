package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.asu.diging.sustainability.core.data.AnnotationRepository;
import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Annotation;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import edu.asu.diging.sustainability.core.service.IAnnotationManager;

public class AnnotationManagerTest {

    @Mock
    private AnnotationRepository annotationRepo;
    
    @InjectMocks
    private IAnnotationManager managerToTest;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void test_listAnnotations_success() {
        Iterable<Annotation> repoResult = new ArrayList<>();
        Annotation an = new Annotation();
        an.setId("ID");
        ((List<Annotation>)repoResult).add(an);
        
        Mockito.when(annotationRepo.findAll()).thenReturn(repoResult);
        
        List<IAnnotation> result = managerToTest.listAnnotations();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("ID", result.get(0).getId());
    }
    
    @Test
    public void test_listAnnotations_empty() {
        Iterable<Annotation> repoResult = new ArrayList<>();
        Mockito.when(annotationRepo.findAll()).thenReturn(repoResult);
        
        List<IAnnotation> result = managerToTest.listAnnotations();
        Assertions.assertEquals(0, result.size());
    }
    
    @Test
    public void test_findTextsForConcepts_oneConcept() {
        IConcept c1 = new Concept();
        c1.setId("C1");
        
        String text1 = "TEXT1";
        String text2 = "TEXT2";
        
        List<IAnnotation> repoResult = new ArrayList<>();
        Annotation an1 = new Annotation();
        an1.setId("ID1");
        an1.setConcept(c1);
        an1.setOccursIn(text1);
        repoResult.add(an1);
        Annotation an2 = new Annotation();
        an2.setId("ID2");
        an2.setConcept(c1);
        an2.setOccursIn(text2);
        repoResult.add(an2);
        
        
        Mockito.when(annotationRepo.findByConceptId("C1")).thenReturn(repoResult);
        Map<String, List<IAnnotation>> results = managerToTest.findTextsForConcepts(new String[] { "C1" });
        Assertions.assertEquals(2, results.keySet().size());
        Assertions.assertEquals(1, results.get(text1).size());
        Assertions.assertEquals(1, results.get(text2).size());
        Assertions.assertTrue(results.get(text1).contains(an1));
        Assertions.assertTrue(results.get(text2).contains(an2));
    }
    
    @Test
    public void test_findTextsForConcepts_severalConcept() {
        IConcept c1 = new Concept();
        c1.setId("C1");
        IConcept c2 = new Concept();
        c2.setId("C2");
        
        String text1 = "TEXT1";
        String text2 = "TEXT2";
        
        List<IAnnotation> repoResult1 = new ArrayList<>();
        Annotation an1 = new Annotation();
        an1.setId("ID1");
        an1.setConcept(c1);
        an1.setOccursIn(text1);
        repoResult1.add(an1);
        Annotation an2 = new Annotation();
        an2.setId("ID2");
        an2.setConcept(c1);
        an2.setOccursIn(text2);
        repoResult1.add(an2);
        
        List<IAnnotation> repoResult2 = new ArrayList<>();
        Annotation an3 = new Annotation();
        an3.setId("ID3");
        an3.setConcept(c2);
        an3.setOccursIn(text2);
        repoResult2.add(an3);
        
        Mockito.when(annotationRepo.findByConceptId("C1")).thenReturn(repoResult1);
        Mockito.when(annotationRepo.findByConceptId("C2")).thenReturn(repoResult2);
        
        // find only text 2, since only this text has both concepts
        Map<String, List<IAnnotation>> results = managerToTest.findTextsForConcepts(new String[] { "C1", "C2" });
        Assertions.assertEquals(1, results.keySet().size());
        Assertions.assertEquals(2, results.get(text2).size());
        Assertions.assertTrue(results.get(text2).contains(an2));
        Assertions.assertTrue(results.get(text2).contains(an3));
    }
    
    @Test
    public void test_findTextsForConcepts_noneFound() {
        IConcept c1 = new Concept();
        c1.setId("C1");
        IConcept c2 = new Concept();
        c2.setId("C2");
        
        String text1 = "TEXT1";
        String text2 = "TEXT2";
        
        List<IAnnotation> repoResult1 = new ArrayList<>();
        Annotation an1 = new Annotation();
        an1.setId("ID1");
        an1.setConcept(c1);
        an1.setOccursIn(text1);
        repoResult1.add(an1);
        
        List<IAnnotation> repoResult2 = new ArrayList<>();
        Annotation an2 = new Annotation();
        an2.setId("ID2");
        an2.setConcept(c2);
        an2.setOccursIn(text2);
        repoResult2.add(an2);
        
        Mockito.when(annotationRepo.findByConceptId("C1")).thenReturn(repoResult1);
        Mockito.when(annotationRepo.findByConceptId("C2")).thenReturn(repoResult2);
        
        // find nothing, since no text has both concepts
        Map<String, List<IAnnotation>> results = managerToTest.findTextsForConcepts(new String[] { "C1", "C2" });
        Assertions.assertEquals(0, results.keySet().size());
    }
}
