package edu.asu.diging.sustainability.core.factory;

import edu.asu.diging.sustainability.core.model.IUser;
import edu.asu.diging.sustainability.web.pages.UserForm;

public interface IUserFactory {

    IUser createUser(UserForm userForm);

}