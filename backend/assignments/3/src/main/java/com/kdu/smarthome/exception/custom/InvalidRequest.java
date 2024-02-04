package com.kdu.smarthome.exception.custom;

public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message) {
        super(message);
    }
}
