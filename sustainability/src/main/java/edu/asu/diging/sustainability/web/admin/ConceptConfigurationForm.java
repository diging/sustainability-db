package edu.asu.diging.sustainability.web.admin;

import java.util.List;

import edu.asu.diging.sustainability.web.admin.pages.ConceptForm;

/**
 * @author namrathaov 
 * Backing form for the edit configuration page.
 */
public class ConceptConfigurationForm {

    private List<ConceptForm> concepts;
    
    public List<ConceptForm> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<ConceptForm> concepts) {
        this.concepts = concepts;
    }

}
