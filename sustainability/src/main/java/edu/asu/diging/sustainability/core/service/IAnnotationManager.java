package edu.asu.diging.sustainability.core.service;

import java.io.IOException;

public interface IAnnotationManager {

    void addAnnotations(byte[] file, String filename) throws IOException;

}