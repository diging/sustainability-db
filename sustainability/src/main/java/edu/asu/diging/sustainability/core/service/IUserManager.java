package edu.asu.diging.sustainability.core.service;

import edu.asu.diging.sustainability.core.model.impl.User;

public interface IUserManager {

    void save(User user);

    User findByUsername(String username);

}