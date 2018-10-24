package edu.asu.diging.sustainability.core.factory.impl;

import org.springframework.stereotype.Component;

import edu.asu.diging.sustainability.core.factory.IUserFactory;
import edu.asu.diging.sustainability.core.model.IUser;
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
}
