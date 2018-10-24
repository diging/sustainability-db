package edu.asu.diging.sustainability.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.asu.diging.sustainability.core.data.UserRepository;
import edu.asu.diging.sustainability.core.exception.UserAlreadyExistsException;
import edu.asu.diging.sustainability.core.model.IUser;
import edu.asu.diging.sustainability.core.model.impl.User;
import edu.asu.diging.sustainability.core.service.IUserManager;

@Service
public class UserService implements UserDetailsService, IUserManager {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findById(arg0);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        
        throw new UsernameNotFoundException(String.format("No user with username %s found.", arg0));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IUserManager#save(edu.asu.diging.sustainability.core.model.impl.User)
     */
    @Override
    public void create(IUser user) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.findById(user.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("The user already exists.");
        }
        user.setEnabled(false);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save((User)user);
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IUserManager#findByUsername(java.lang.String)
     */
    @Override
    public IUser findByUsername(String username) {
        Optional<User> foundUser = userRepository.findById(username);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        return null;
    }
}
