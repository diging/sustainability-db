package edu.asu.diging.sustainability.core.model.impl;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import edu.asu.diging.sustainability.core.model.IAnnotation;
import edu.asu.diging.sustainability.core.model.IMetadatum;
import edu.asu.diging.sustainability.core.model.IResource;

/**
 * This class represents resources stored in Amphora. Resources are referred to
 * in {@link IAnnotation} by their URI.
 * 
 * 
 * @author jdamerow
 *
 */
@Entity
public class Resource implements IResource {

    @Id
    private String uri;
    
    @ElementCollection(targetClass=Metadatum.class)
    @CollectionTable(
            joinColumns=@JoinColumn(name="uri")
      )
    private List<IMetadatum> metadata;
    
    private ZonedDateTime updatedOn;
    
    @Override
    public String getTitle() {
        for (IMetadatum datum : metadata) {
            if (datum.getPredicate().equals(TITLE_PREDICATE)) {
                return datum.getValue();
            }
        }
        return null;
    }
    
    @Override
    public String getYear() {
        for (IMetadatum datum : metadata) {
            if (datum.getPredicate().equals(YEAR_PREDICATE)) {
                return datum.getValue();
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IResource#getUri()
     */
    @Override
    public String getUri() {
        return uri;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IResource#setUri(java.lang.String)
     */
    @Override
    public void setUri(String uri) {
        this.uri = uri;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IResource#getMetadata()
     */
    @Override
    public List<IMetadatum> getMetadata() {
        return metadata;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IResource#setMetadata(java.util.List)
     */
    @Override
    public void setMetadata(List<IMetadatum> metadata) {
        this.metadata = metadata;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IResource#getUpdatedOn()
     */
    @Override
    public ZonedDateTime getUpdatedOn() {
        return updatedOn;
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IResource#setUpdatedOn(java.time.ZonedDateTime)
     */
    @Override
    public void setUpdatedOn(ZonedDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
    
}
