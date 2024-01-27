package com.caching.exceptions;

import com.caching.dto.ErrorDTO;
import com.caching.exceptions.custom.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Global exception handler for handling custom exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MyException and returns an error response with a custom message and HTTP status code.
     *
     * @param ex the MyException instance.
     * @return ResponseEntity containing an ErrorDTO and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorDTO> handleMyException(MyException ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IOException and returns an error response with an empty message and HTTP status code BAD_REQUEST.
     *
     * @param ex the IOException instance.
     * @return ResponseEntity containing an ErrorDTO with an empty message and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException ex) {
        ErrorDTO errorDTO = new ErrorDTO("", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
