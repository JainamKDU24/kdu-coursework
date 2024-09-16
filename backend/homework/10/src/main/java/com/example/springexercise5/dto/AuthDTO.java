package com.example.springexercise5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for authentication.
 */
@Data
@AllArgsConstructor
public class AuthDTO {
    /**
     * The username for authentication.
     */
    String username;

    /**
     * The password for authentication.
     */
    String password;
}
