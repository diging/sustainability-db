package edu.asu.diging.sustainability.web;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import org.springframework.context.annotation.PropertySource;
import edu.asu.diging.sustainability.core.model.impl.Roles;

@PropertySource("classpath:config.properties")
public class ConceptForm {

    private String id;

    @ElementCollection(targetClass = Roles.class)
    @Enumerated(EnumType.STRING)
    @JoinColumn(name="concept_id")
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
