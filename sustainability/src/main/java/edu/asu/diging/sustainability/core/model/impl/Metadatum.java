package edu.asu.diging.sustainability.core.model.impl;

import javax.persistence.Embeddable;

import edu.asu.diging.sustainability.core.model.IMetadatum;

@Embeddable
public class Metadatum implements IMetadatum {

    private String label;
    private String value;
    private String predicate;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IMetadatum#getLabel()
     */
    @Override
    public String getLabel() {
        return label;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IMetadatum#setLabel(java.lang.String)
     */
    @Override
    public void setLabel(String label) {
        this.label = label;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IMetadatum#getValue()
     */
    @Override
    public String getValue() {
        return value;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IMetadatum#setValue(java.lang.String)
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IMetadatum#getPredicate()
     */
    @Override
    public String getPredicate() {
        return predicate;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IMetadatum#setPredicate(java.lang.String)
     */
    @Override
    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }
}
