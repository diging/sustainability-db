package edu.asu.diging.sustainability.core.model;

import java.util.Set;

import edu.asu.diging.sustainability.core.model.impl.User;

public interface Role {

    Long getId();

    void setId(Long id);

    Set<User> getUsers();

    void setUsers(Set<User> users);

}