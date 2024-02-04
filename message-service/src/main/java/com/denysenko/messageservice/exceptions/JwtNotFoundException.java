package com.denysenko.messageservice.exceptions;

public class JwtNotFoundException extends RuntimeException{
    public JwtNotFoundException() {
    }

    public JwtNotFoundException(String message) {
        super(message);
    }
}
