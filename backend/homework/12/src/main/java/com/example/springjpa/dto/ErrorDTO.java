package com.example.springjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for representing error messages.
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    /**
     * The error message.
     */
    String message;

    /**
     * The status code associated with the error.
     */
    int statusCode;
}
