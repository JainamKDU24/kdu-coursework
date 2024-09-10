package com.kdu.smarthome.exception;

import com.kdu.smarthome.dto.reponse.ErrorDTO;
import com.kdu.smarthome.exception.custom.CouldNotGet;
import com.kdu.smarthome.exception.custom.NotPermitted;
import com.kdu.smarthome.exception.custom.InvalidRequest;
import com.kdu.smarthome.exception.custom.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler class for handling exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for MyCustomException.
     *
     * @param ex The MyCustomException to handle.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(value = {NotFound.class})
    public ResponseEntity<ErrorDTO> handleCustomException(NotFound ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {InvalidRequest.class})
    public ResponseEntity<ErrorDTO> handleInvalidRequestException(InvalidRequest ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {NotPermitted.class})
    public ResponseEntity<ErrorDTO> handleNotPermittedException(NotPermitted ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = {CouldNotGet.class})
    public ResponseEntity<ErrorDTO> handleCouldNotGetException(CouldNotGet ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
