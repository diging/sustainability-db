package edu.asu.diging.sustainability.core.model;

public interface IAnnotation {

    void setFromFile(String fromFile);

    String getFromFile();

    void setConcept(IConcept concept);

    IConcept getConcept();

    void setOccursIn(String occursIn);

    String getOccursIn();

    void setEnd(int end);

    int getEnd();

    void setStart(int start);

    int getStart();

    void setSegment(String segment);

    String getSegment();

    void setId(String id);

    String getId();

}