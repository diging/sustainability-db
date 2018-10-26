package edu.asu.diging.sustainability.core.service;

import java.util.List;

import edu.asu.diging.sustainability.core.exception.UserAlreadyExistsException;
import edu.asu.diging.sustainability.core.model.IUser;

public interface IUserManager {

    void create(IUser user) throws UserAlreadyExistsException;

    IUser findByUsername(String username);

    List<IUser> findAll();

    void approveAccount(String username, String approver);

}