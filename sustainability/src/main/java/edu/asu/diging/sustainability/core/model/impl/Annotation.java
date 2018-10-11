package edu.asu.diging.sustainability.core.model.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.IConcept;

@Entity
public class Annotation implements IAnnotation {

    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator",    
                    parameters = @Parameter(name = "prefix", value = "ANN"), 
                    strategy = "edu.asu.diging.sustainability.core.data.IdGenerator"
            )
	private String id;
    
    @Lob 
    private String segment;
	private int start;
	private int end;
	private String occursIn;
	@ManyToOne(targetEntity=Concept.class)
	private IConcept concept;
	private String fromFile;
	
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String getSegment() {
        return segment;
    }
    @Override
    public void setSegment(String segment) {
        this.segment = segment;
    }
    @Override
    public int getStart() {
        return start;
    }
    @Override
    public void setStart(int start) {
        this.start = start;
    }
    @Override
    public int getEnd() {
        return end;
    }
    @Override
    public void setEnd(int end) {
        this.end = end;
    }
    @Override
    public String getOccursIn() {
        return occursIn;
    }
    @Override
    public void setOccursIn(String occursIn) {
        this.occursIn = occursIn;
    }
    @Override
    public IConcept getConcept() {
        return concept;
    }
    @Override
    public void setConcept(IConcept concept) {
        this.concept = concept;
    }
    @Override
    public String getFromFile() {
        return fromFile;
    }
    @Override
    public void setFromFile(String fromFile) {
        this.fromFile = fromFile;
    }
	
}
