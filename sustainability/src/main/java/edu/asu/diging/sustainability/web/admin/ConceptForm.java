package edu.asu.diging.sustainability.web.admin;

import java.util.List;
import edu.asu.diging.sustainability.core.model.impl.SearchCategory;

/**
 * @author namrathaov 
 * Concept replica for the edit configuration form to use in storing configuration.
 */
public class ConceptForm {

    private String id;

    private List<SearchCategory> searchCategories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SearchCategory> getSearchCategories() {
        return searchCategories;
    }

    public void setSearchCategories(List<SearchCategory> searchCategories) {
        this.searchCategories = searchCategories;
    }

}
