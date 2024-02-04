package com.kdu.smarthome.exception.custom;

import org.springframework.http.HttpStatus;

public class NotPermitted extends RuntimeException{
    private HttpStatus httpStatus;
    public NotPermitted(String message) {
        super(message);
    }
    public NotPermitted(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}