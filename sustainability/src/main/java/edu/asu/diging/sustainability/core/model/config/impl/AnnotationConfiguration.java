package edu.asu.diging.sustainability.core.model.config.impl;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.config.DisplayType;
import edu.asu.diging.sustainability.core.model.config.IAnnotationConfiguration;
import edu.asu.diging.sustainability.core.model.config.Section;
import edu.asu.diging.sustainability.core.model.impl.Concept;

@Entity
public class AnnotationConfiguration implements IAnnotationConfiguration {

    @Id
    @GeneratedValue(generator = "anno-config-id-generator")
    @GenericGenerator(name = "anno-config-id-generator",    
                    parameters = @Parameter(name = "prefix", value = "AC"), 
                    strategy = "edu.asu.diging.sustainability.core.data.IdGenerator"
            )
    private String id;
    
    @ManyToOne(targetEntity=Concept.class)
    private IConcept concept;
    
    private int sortOrder;
    
    @Enumerated(EnumType.STRING)
    private Section section;
    
    @Enumerated(EnumType.STRING)
    private DisplayType displayType;
    
    public AnnotationConfiguration() {}

    public AnnotationConfiguration(IConcept concept) {
        this.concept = concept;
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#getId()
     */
    @Override
    public String getId() {
        return id;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#setId(java.lang.String)
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#getConcept()
     */
    @Override
    public IConcept getConcept() {
        return concept;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#setConcept(edu.asu.diging.sustainability.core.model.IConcept)
     */
    @Override
    public void setConcept(IConcept concept) {
        this.concept = concept;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#getOrder()
     */
    @Override
    public int getSortOrder() {
        return sortOrder;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#setOrder(int)
     */
    @Override
    public void setSortOrder(int order) {
        this.sortOrder = order;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#getSection()
     */
    @Override
    public Section getSection() {
        return section;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IAnnotationConfiguration#setSection(edu.asu.diging.sustainability.core.model.impl.Section)
     */
    @Override
    public void setSection(Section section) {
        this.section = section;
    }
    @Override
    public DisplayType getDisplayType() {
        return displayType;
    }
    @Override
    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }
    
    
}
