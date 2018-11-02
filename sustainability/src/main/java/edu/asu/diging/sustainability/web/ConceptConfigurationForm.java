package edu.asu.diging.sustainability.web;

import java.util.List;
import edu.asu.diging.sustainability.core.model.IConcept;
import edu.asu.diging.sustainability.core.model.impl.Roles;
public class ConceptConfigurationForm {

    
    private IConcept concept;
    
    public IConcept getConcept() {
        return concept;
    }

    public void setConcept(IConcept concept) {
        this.concept = concept;
    }

    private List<String> roles;
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
