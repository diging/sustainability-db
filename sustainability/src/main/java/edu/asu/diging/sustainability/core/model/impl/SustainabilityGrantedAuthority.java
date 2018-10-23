package edu.asu.diging.sustainability.core.model.impl;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import edu.asu.diging.sustainability.core.model.Role;

@Entity
public class SustainabilityGrantedAuthority implements Role, GrantedAuthority {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authority;
    @ElementCollection(targetClass=User.class)
    private Set<User> users;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IRole#getId()
     */
    @Override
    public Long getId() {
        return id;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IRole#setId(java.lang.Long)
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IRole#getUsers()
     */
    @Override
    public Set<User> getUsers() {
        return users;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IRole#setUsers(java.util.Set)
     */
    @Override
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    @Override
    public String getAuthority() {
        return authority;
    }

    
}