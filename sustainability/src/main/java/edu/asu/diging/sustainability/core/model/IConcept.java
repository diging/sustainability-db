package edu.asu.diging.sustainability.core.model;

import java.util.List;
import edu.asu.diging.sustainability.core.model.impl.Roles;

public interface IConcept {

    void setChildren(List<IConcept> children);

    List<IConcept> getChildren();

    void setParent(IConcept parent);

    IConcept getParent();

    void setUri(String uri);

    String getUri();

    void setName(String name);

    String getName();

    void setId(String id);

    String getId();

    void setRoles(List<Roles> roles);

    List<Roles> getRoles();


}