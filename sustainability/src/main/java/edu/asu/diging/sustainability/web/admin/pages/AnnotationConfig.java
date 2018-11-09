package edu.asu.diging.sustainability.web.admin.pages;

import edu.asu.diging.sustainability.core.model.config.DisplayType;
import edu.asu.diging.sustainability.core.model.config.Section;

public class AnnotationConfig {

    private String id;
    private String conceptId;
    private String conceptName;
    private int sortOrder;
    private Section section;
    private DisplayType displayType;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getConceptId() {
        return conceptId;
    }
    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }
    public String getConceptName() {
        return conceptName;
    }
    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }
    public int getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Section getSection() {
        return section;
    }
    public void setSection(Section section) {
        this.section = section;
    }
    public DisplayType getDisplayType() {
        return displayType;
    }
    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }
    
    
}
