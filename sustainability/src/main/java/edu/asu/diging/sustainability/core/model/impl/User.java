package edu.asu.diging.sustainability.core.model.impl;

import java.util.Collection;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.asu.diging.sustainability.core.model.IUser;

@Entity
public class User implements IUser, UserDetails {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ElementCollection(targetClass=SustainabilityGrantedAuthority.class, fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    private Set<SustainabilityGrantedAuthority> roles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String notes;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#getUsername()
     */
    @Override
    public String getUsername() {
        return username;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#setUsername(java.lang.String)
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#getFirstName()
     */
    @Override
    public String getFirstName() {
        return firstName;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#setFirstName(java.lang.String)
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#getLastName()
     */
    @Override
    public String getLastName() {
        return lastName;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#setLastName(java.lang.String)
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#getEmail()
     */
    @Override
    public String getEmail() {
        return email;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#setEmail(java.lang.String)
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#getPassword()
     */
    @Override
    public String getPassword() {
        return password;
    }
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.model.impl.IUser#setPassword(java.lang.String)
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    public void setRoles(Set<SustainabilityGrantedAuthority> roles) {
        this.roles = roles;
        
    }
    
    @Override
    public Set<SustainabilityGrantedAuthority> getRoles() {
        return roles;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    @Override
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    @Override
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public String getNotes() {
        return notes;
    }
    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }  
}