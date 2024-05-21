package com.xidesk.security.authentication;

import com.xidesk.security.request.AuthenticationRequest;
import com.xidesk.security.request.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping()
    public ResponseEntity<Void> authenticate(
            HttpServletResponse response,
            @RequestBody AuthenticationRequest authentication
    ) {
        authenticationService.authenticate(authentication, response);
        return ResponseEntity.status(200).build();
    }

    @PostMapping()
    public ResponseEntity<Void> registration(
            HttpServletResponse response,
            @RequestBody RegistrationRequest request
    ) {
        authenticationService.register(request, response);
        return ResponseEntity.status(200).build();
    }


}
