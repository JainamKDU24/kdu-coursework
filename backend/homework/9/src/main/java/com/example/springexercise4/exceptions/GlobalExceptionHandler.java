package com.example.springexercise4.exceptions;

import com.example.springexercise4.dto.response.ErrorDTO;
import com.example.springexercise4.exceptions.custom.myexception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(myexception.class)
    public ResponseEntity<ErrorDTO> handleException(myexception ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleException(HttpMessageNotReadableException ex) {
        ErrorDTO errorDTO = new ErrorDTO("Missing Fields in the JSON Body",HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }
}
