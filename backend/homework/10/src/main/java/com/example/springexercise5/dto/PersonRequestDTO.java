package com.example.springexercise5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for representing a request to create a person.
 */
@Data
@AllArgsConstructor
public class PersonRequestDTO {
    /**
     * The name of the person.
     */
    private String name;
}
