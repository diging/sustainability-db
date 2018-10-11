package edu.asu.diging.sustainability.core.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.asu.diging.sustainability.core.model.IAnnotation;

public interface IAnnotationManager {

    void addAnnotations(byte[] file, String filename) throws IOException;

    Map<String, List<IAnnotation>> findTextsForConcepts(String[] conceptIds);

}