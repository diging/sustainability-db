package edu.asu.diging.sustainability.web;

import java.util.List;
import edu.asu.diging.sustainability.core.model.impl.Roles;

/**
 * @author Namratha 
 * Concept replica for the edit configuration form to use in storing configuration.
 */
public class ConceptForm {

    private String id;

    private List<Roles> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

}
