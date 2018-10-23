package edu.asu.diging.sustainability.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.asu.diging.sustainability.core.data.UserRepository;
import edu.asu.diging.sustainability.core.model.impl.User;
import edu.asu.diging.sustainability.core.service.IUserManager;

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
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IUserManager#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username) {
        Optional<User> foundUser = userRepository.findById(username);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        return null;
    }
}
