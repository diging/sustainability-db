package edu.asu.diging.sustainability.core.model;

import java.util.Set;

import edu.asu.diging.sustainability.core.model.impl.SustainabilityGrantedAuthority;

public interface IUser {

    String getUsername();

    void setUsername(String username);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    void setRoles(Set<SustainabilityGrantedAuthority> roles);

    void setEnabled(boolean enabled);

    void setCredentialsNonExpired(boolean credentialsNonExpired);

    void setAccountNonLocked(boolean accountNonLocked);

    void setAccountNonExpired(boolean accountNonExpired);

    void setNotes(String notes);

    String getNotes();

    Set<SustainabilityGrantedAuthority> getRoles();

}