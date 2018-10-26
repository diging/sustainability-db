package edu.asu.diging.sustainability.core.model.impl;

import javax.persistence.Embeddable;

import org.springframework.security.core.GrantedAuthority;

import edu.asu.diging.sustainability.core.model.Role;

@Embeddable
public class SustainabilityGrantedAuthority implements Role, GrantedAuthority {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String authority;
     
    public SustainabilityGrantedAuthority() {} 
    
    public SustainabilityGrantedAuthority(String authority) {
        this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        return authority;
    }

    
}