package edu.asu.diging.sustainability.core.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;

import edu.asu.diging.sustainability.core.data.AnnotationRepository;
import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Annotation;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import edu.asu.diging.sustainability.core.service.IAnnotationManager;

@Service
@Transactional
public class AnnotationManager implements IAnnotationManager {

    private final String SEGMENT = "Segment";
    private final String START = "Start";
    private final String END = "End";
    private final String OCCURS = "Occurs";
    private final String CONCEPT = "Concept";

    @Autowired
    private AnnotationRepository annotationRepo;
    
    @Autowired
    private ConceptRepository conceptRepo;

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IAnnotationManager#addAnnotations(byte[], java.lang.String)
     */
    @Override
    public void addAnnotations(byte[] file, String filename) throws IOException {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        CSVReaderHeaderAwareBuilder csvReaderBuilder = (CSVReaderHeaderAwareBuilder) new CSVReaderHeaderAwareBuilder(new InputStreamReader(new ByteArrayInputStream(file), Charset.forName("utf-8")))
                .withCSVParser(rfc4180Parser);
        try (CSVReaderHeaderAware csvReader = csvReaderBuilder.build()) {
            Map<String, String> nextRecord;
            while ((nextRecord = csvReader.readMap()) != null) {
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
                        conceptRepo.save((Concept)parent);
                        conceptRepo.save((Concept)annotation.getConcept());
                    }
                }
                
                annotationRepo.save(annotation);
            }
        }
    }
}
