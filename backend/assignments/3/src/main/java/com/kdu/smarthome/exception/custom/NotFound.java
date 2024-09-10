package com.kdu.smarthome.exception.custom;

/**
 * Custom exception class representing a specific exception in the application.
 */
public class NotFound extends RuntimeException {
    /**
     * Constructs a new MyCustomException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public NotFound(String message) {
        super(message);
    }
}
