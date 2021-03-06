package edu.asu.diging.sustainability.core.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.diging.sustainability.core.data.AnnotationRepository;
import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.exception.NotAValidResourceException;
import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Annotation;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import edu.asu.diging.sustainability.core.service.IAnnotationManager;
import edu.asu.diging.sustainability.core.service.IResourceManager;

@Service
@Transactional
public class AnnotationManager implements IAnnotationManager {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String SEGMENT = "Segment";
    private final String START = "Start";
    private final String END = "End";
    private final String OCCURS = "Occurs";
    private final String CONCEPT = "Concept";

    @Autowired
    private AnnotationRepository annotationRepo;

    @Autowired
    private ConceptRepository conceptRepo;
    
    @Autowired
    private IResourceManager resourceManager;

    @Override
    public List<IAnnotation> listAnnotations() {
        List<IAnnotation> annotations = new ArrayList<>();
        annotationRepo.findAll().forEach(a -> annotations.add(a));
        return annotations;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see edu.asu.diging.sustainability.core.service.impl.IAnnotationManager#
     * addAnnotations(byte[], java.lang.String)
     */
    @Override
    public void addAnnotations(byte[] file, String filename) throws IOException {

        try (Reader in = new InputStreamReader(new ByteArrayInputStream(file), Charset.forName("utf-8"));) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreEmptyLines(true).parse(in);
            for (CSVRecord nextRecord : records) {
                Annotation annotation = createAnnotation(filename, nextRecord);
                annotationRepo.save(annotation);
                try {
                    resourceManager.getResource(annotation.getOccursIn());
                } catch (NotAValidResourceException e) {
                    logger.warn("Could not import resource metadata.", e);
                }
            }
        }
    }

    private Annotation createAnnotation(String filename, CSVRecord nextRecord) {
        Annotation annotation = new Annotation();
        annotation.setSegment(nextRecord.get(SEGMENT));
        annotation.setEnd(new Integer(nextRecord.get(END)));
        annotation.setStart(new Integer(nextRecord.get(START)));
        annotation.setOccursIn(nextRecord.get(OCCURS));
        annotation.setFromFile(filename);

        String name = nextRecord.get(CONCEPT);
        // create hierachy
        String[] parts = name.split("\\\\");
        IConcept parent = null;
        for (String part : parts) {
            List<Concept> concepts = conceptRepo.findByName(part);
            if (concepts.size() > 0) {
                // there should be only one
                annotation.setConcept(concepts.get(0));
            } else {
                Concept concept = new Concept();
                concept.setName(part);
                conceptRepo.save(concept);
                annotation.setConcept(concept);
            }

            if (parent == null) {
                parent = annotation.getConcept();
            } else {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                IConcept child = annotation.getConcept();
                if (child.getParent() != null) {
                    child.getParent().getChildren().remove(child);
                    child.setParent(null);
                }
                parent.getChildren().add(child);
                annotation.getConcept().setParent(parent);
                conceptRepo.save((Concept) parent);
                conceptRepo.save((Concept) annotation.getConcept());
            }
        }
        return annotation;
    }

    @Override
    public List<IAnnotation> getAnnotationsForText(String uri) {
        return annotationRepo.findByUri(uri);
    }
    
    @Override
    public Map<IConcept, List<IAnnotation>> getAnnotationsForTextByConceptHierachy(String uri) {
        List<IAnnotation> annotations = getAnnotationsForText(uri);
        Map<IConcept, List<IAnnotation>> byParentConcept = new HashMap<>();
        annotations.forEach(a -> {
            IConcept parent = a.getConcept().getParent();
            if (byParentConcept.get(parent) == null) {
                byParentConcept.put(parent, new ArrayList<>());
            }
            byParentConcept.get(parent).add(a);
        });
        return byParentConcept;
    }
    
    @Override
    public Map<String, List<IAnnotation>> findTextsForConcepts(String[] conceptIds) {
        Map<String, List<IAnnotation>> results = new HashMap<>();
        Map<String, Set<String>> textsByConcepts = new HashMap<>();
        for (String id : conceptIds) {
            List<IAnnotation> annotations = annotationRepo.findByConceptId(id);
            annotations.forEach(a -> {
                String text = a.getOccursIn();
                if (results.get(text) == null) {
                    results.put(text, new ArrayList<>());
                }
                results.get(text).add(a);
                if (textsByConcepts.get(text) == null) {
                    textsByConcepts.put(text, new HashSet<>());
                }
                textsByConcepts.get(text).add(id);
            });
        }

        List<String> toBeRemoved = new ArrayList<>();
        for (String textUri : results.keySet()) {
            // if all concepts appear in text
            if (textsByConcepts.get(textUri).size() != conceptIds.length) {
                toBeRemoved.add(textUri);
            }
        }

        toBeRemoved.forEach(r -> results.remove(r));
        return results;
    }
}
