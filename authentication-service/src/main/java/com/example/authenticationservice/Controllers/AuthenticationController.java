package com.example.authenticationservice.Controllers;

import com.example.authenticationservice.Model.User;
import com.example.authenticationservice.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        boolean isValid = authenticationService.validateToken(token);
        if (isValid) {
            return new ResponseEntity<>("Token is valid", HttpStatus.OK);
        }
        return new ResponseEntity<>("Token is not valid", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody User user) {
        if (!authenticationService.authenticateUser(user.getUsername(), user.getPassword())) {
            return new ResponseEntity<>("Wrong credentials try again", HttpStatus.UNAUTHORIZED);
        }
        String token = authenticationService.generateAccessToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}