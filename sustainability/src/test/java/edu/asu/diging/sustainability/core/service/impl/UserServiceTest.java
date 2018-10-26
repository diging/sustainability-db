package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

import edu.asu.diging.sustainability.core.data.UserRepository;
import edu.asu.diging.sustainability.core.factory.IUserFactory;
import edu.asu.diging.sustainability.core.model.IUser;
import edu.asu.diging.sustainability.core.model.Role;
import edu.asu.diging.sustainability.core.model.impl.SustainabilityGrantedAuthority;
import edu.asu.diging.sustainability.core.model.impl.User;
import junit.framework.Assert;

public class UserServiceTest {
    
    @Mock
    private Properties properties;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private IUserFactory userFactory;
    
    @InjectMocks
    private UserService serviceToTest;
    
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PW_ENCODED = "$2a$04$rHKnblcgQFsGPpqaEKPjAOkOy6PSBKefmeP8Rs7woocvR0fmzn3eq";
    private final String ENABLED = "enabled";
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(properties.getProperty("admin")).thenReturn(String.format("%s,%s,%s", ADMIN_PW_ENCODED, Role.ADMIN, ENABLED));
        IUser user = new User();
        user.setUsername(ADMIN_USERNAME);
        user.setPassword(ADMIN_PW_ENCODED);
        user.setRoles(new HashSet<>(Arrays.asList(new SustainabilityGrantedAuthority(Role.ADMIN))));
        user.setEnabled(true);
        Mockito.when(userFactory.createUser(ADMIN_USERNAME, ADMIN_PW_ENCODED, Role.ADMIN, true)).thenReturn(user);
    }

    @Test
    public void test_loadUserByUsername_admin_success() {
        Mockito.when(userRepository.findById(ADMIN_USERNAME)).thenReturn(Optional.empty());
        UserDetails user = serviceToTest.loadUserByUsername(ADMIN_USERNAME);
        Assert.assertEquals(ADMIN_USERNAME, user.getUsername());
        Assert.assertEquals(ADMIN_PW_ENCODED, user.getPassword());
        Assert.assertEquals(Role.ADMIN, user.getAuthorities().iterator().next().getAuthority());
        Assert.assertTrue(user.isEnabled());
    }
    
    @Test
    public void test_loadUserByUsername_regular_success() {
        IUser user = new User();
        user.setUsername("USER");
        user.setPassword("PW");
        user.setRoles(new HashSet<>(Arrays.asList(new SustainabilityGrantedAuthority(Role.ADMIN))));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        Mockito.when(userRepository.findById("USER")).thenReturn(Optional.of((User)user));
        
        UserDetails foundUser = serviceToTest.loadUserByUsername("USER");
        Assert.assertEquals(user, foundUser);
    }
    
    @Test 
    public void test_loadUserByUsername_fail() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> serviceToTest.loadUserByUsername("TEST"));
    }
}
