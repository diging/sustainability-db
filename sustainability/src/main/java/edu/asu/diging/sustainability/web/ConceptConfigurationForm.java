package edu.asu.diging.sustainability.web;

import java.util.List;

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
