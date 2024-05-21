package com.xidesk.user;

import com.xidesk.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(username);
        if (optionalUser.isEmpty()) {
            logger.warn("User '{}' not found", username);
            throw new UserNotFoundException(String.format("User %s not found", username));
        }
        return optionalUser.get();
    }


    public User saveUser(User user) {
        if (!user.isValid()) {
            logger.warn("Given user is not valid");
            throw new NullPointerException("Given user is not valid");
        }
        return userRepository.save(user);
    }






}
