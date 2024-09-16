package com.example.springexercise5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication-related endpoints.
 */
@RestController
public class AuthController {

    /**
     * Handles the endpoint for user login.
     *
     * @return A ResponseEntity with a message indicating successful login.
     */
    @GetMapping("/person/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("Login Done!", HttpStatus.CREATED);
    }
}
