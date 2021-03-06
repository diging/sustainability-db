package edu.asu.diging.sustainability.web.admin.pages;

import java.util.List;

import edu.asu.diging.sustainability.core.model.SearchCategory;

/**
 * @author namrathaov 
 * Concept replica for the edit configuration form to use in storing configuration.
 */
public class ConceptForm {

    private String id;

    private String conceptAlias;

    private List<SearchCategory> searchCategories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConceptAlias() {
        return conceptAlias;
    }

    public void setConceptAlias(String conceptAlias) {
        this.conceptAlias = conceptAlias;
    }

    public List<SearchCategory> getSearchCategories() {
        return searchCategories;
    }

    public void setSearchCategories(List<SearchCategory> searchCategories) {
        this.searchCategories = searchCategories;
    }

}
