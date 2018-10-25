package edu.asu.diging.sustainability.core.factory.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.asu.diging.sustainability.core.factory.IUserFactory;
import edu.asu.diging.sustainability.core.model.IUser;
import edu.asu.diging.sustainability.core.model.impl.SustainabilityGrantedAuthority;
import edu.asu.diging.sustainability.core.model.impl.User;
import edu.asu.diging.sustainability.web.pages.UserForm;

@Component
public class UserFactory implements IUserFactory {

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.factory.impl.IUserFactory#createUser(edu.asu.diging.sustainability.web.pages.UserForm)
     */
    @Override
    public IUser createUser(UserForm userForm) {
        IUser user = new User();
        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());
        user.setUsername(userForm.getUsername());
        
        return user;
    }
    
    @Override
    public IUser createUser(String username, String password, String role, boolean enabled) {
        IUser user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        
        Set<SustainabilityGrantedAuthority> roles = new HashSet<>();
        roles.add(new SustainabilityGrantedAuthority(role));
        user.setRoles(roles);
        return user;
    }
}
