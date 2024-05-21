package com.xidesk.security.authentication;

import com.xidesk.security.JwtService;
import com.xidesk.security.request.AuthenticationRequest;
import com.xidesk.security.request.RegistrationRequest;
import com.xidesk.user.Role;
import com.xidesk.user.User;
import com.xidesk.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(
            JwtService jwtService,
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void authenticate(
            AuthenticationRequest request,
            HttpServletResponse response
    ) {
        User user = userService.loadUserByUsername(request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(user);
        response.addHeader("Authorization", "Bearer " + token);
    }

    public void register(
            RegistrationRequest request,
            HttpServletResponse response
    ) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
        userService.saveUser(user);
        String token = jwtService.generateToken(user);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
