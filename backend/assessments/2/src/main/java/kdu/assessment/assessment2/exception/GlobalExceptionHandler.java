package kdu.assessment.assessment2.exception;

import kdu.assessment.assessment2.dto.ErrorDTO;
import kdu.assessment.assessment2.exception.custom.CartNotFound;
import kdu.assessment.assessment2.exception.custom.MyCustomException;
import kdu.assessment.assessment2.exception.custom.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [MyCustomException]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleShiftUserNotFoundException(ProductNotFound ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CartNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleShiftUserNotFoundException(CartNotFound ex) {
        return ex.getMessage();
    }

}
