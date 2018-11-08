package edu.asu.diging.sustainability.core.model.config;

import edu.asu.diging.sustainability.core.model.IConcept;

public interface IAnnotationConfiguration {

    String getId();

    void setId(String id);

    IConcept getConcept();

    void setConcept(IConcept concept);

    int getSortOrder();

    void setSortOrder(int order);

    Section getSection();

    void setSection(Section section);

    void setDisplayType(DisplayType displayType);

    DisplayType getDisplayType();


}