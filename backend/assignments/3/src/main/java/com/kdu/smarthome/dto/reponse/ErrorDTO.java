package com.kdu.smarthome.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

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
    HttpStatus statusCode;
}
