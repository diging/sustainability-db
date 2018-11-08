package edu.asu.diging.sustainability.core.model;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public interface IResource {
    
    public final static String TITLE_PREDICATE = "http://purl.org/dc/terms/title";
    public final static String YEAR_PREDICATE = "http://dbpedia.org/ontology/year";
    
    public final static String PROVENANCE_PREDICATE = "http://purl.org/dc/terms/provenance";
    
    String getUri();

    void setUri(String uri);

    List<IMetadatum> getMetadata();

    void setMetadata(List<IMetadatum> metadata);

    ZonedDateTime getUpdatedOn();

    void setUpdatedOn(ZonedDateTime updatedOn);

    String getTitle();

    String getYear();

    List<IMetadatum> getOthers();

}