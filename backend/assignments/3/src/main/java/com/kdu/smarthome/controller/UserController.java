package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.reponse.RegisterResponseDTO;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(userDTO));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
