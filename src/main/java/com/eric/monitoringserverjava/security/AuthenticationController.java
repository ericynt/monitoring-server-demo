package com.eric.monitoringserverjava.security;

import com.eric.monitoringserverjava.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;
    
    @Autowired
    public AuthenticationController (AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    @RequestMapping(value = "/authentications", method = RequestMethod.POST)
    public String authenticate (@RequestBody User user) {
        return authenticationService.authenticate(user);
    }
}
