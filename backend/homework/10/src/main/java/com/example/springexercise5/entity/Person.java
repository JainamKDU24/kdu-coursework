package com.example.springexercise5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Entity class representing a person.
 */
@Data
@AllArgsConstructor
public class Person {
    /**
     * The name of the person.
     */
    private String name;

    /**
     * The username of the person.
     */
    private String username;

    /**
     * The password of the person.
     */
    private String password;

    /**
     * The email of the person.
     */
    private String email;

    /**
     * The role of the person.
     */
    private String role;
}
