package com.caching.exceptions.custom;

/**
 * Custom exception class to represent application-specific exceptions.
 */
public class MyException extends RuntimeException {

    /**
     * Constructs a new custom exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public MyException(String message) {
        super(message);
    }
}
