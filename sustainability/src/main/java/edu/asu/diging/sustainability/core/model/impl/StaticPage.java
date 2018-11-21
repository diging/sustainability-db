package edu.asu.diging.sustainability.core.model.impl;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import edu.asu.diging.sustainability.core.model.IStaticPage;
import edu.asu.diging.sustainability.core.model.PageType;

@Entity
public class StaticPage implements IStaticPage {

    @Id
    @GeneratedValue(generator = "page-id-generator")
    @GenericGenerator(name = "page-id-generator",
            parameters = @Parameter(name = "prefix", value = "PA"),
            strategy = "edu.asu.diging.sustainability.core.data.IdGenerator")
    public String id;
    public String title;
    @Lob public String content;
    @Enumerated(EnumType.STRING)
    public PageType pageType;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#getId()
     */
    @Override
    public String getId() {
        return id;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#setId(java.lang.String)
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#getName()
     */
    @Override
    public String getTitle() {
        return title;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#setName(java.lang.String)
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#getContent()
     */
    @Override
    public String getContent() {
        return content;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#setContent(java.lang.String)
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#getPageType()
     */
    @Override
    public PageType getPageType() {
        return pageType;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IStaticPage#setPageType(edu.asu.diging.sustainability.core.model.PageType)
     */
    @Override
    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }    
}
