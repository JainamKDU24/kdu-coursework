package com.example.springexercise5.exception.custom;

/**
 * Custom exception class representing a specific exception in the application.
 */
public class MyCustomException extends IndexOutOfBoundsException {
    /**
     * Constructs a new MyCustomException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MyCustomException(String message) {
        super(message);
    }
}
