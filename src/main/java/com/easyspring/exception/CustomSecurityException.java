package com.easyspring.exception;

import org.springframework.http.HttpStatus;

public class CustomSecurityException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;
    public CustomSecurityException(String msg, HttpStatus status){
        this.message =msg;
        this.httpStatus = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
