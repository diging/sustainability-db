package edu.asu.diging.sustainability.core.service;

import edu.asu.diging.sustainability.core.exception.UserAlreadyExistsException;
import edu.asu.diging.sustainability.core.model.IUser;

public interface IUserManager {

    void create(IUser user) throws UserAlreadyExistsException;

    IUser findByUsername(String username);

}