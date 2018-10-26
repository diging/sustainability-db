package edu.asu.diging.sustainability.core.model;

import java.util.List;

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

    List<String> getRoles();

    void addRole(String role);

}