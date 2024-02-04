package com.denysenko.botuserservice.exceptions;

public class JwtNotFoundException extends RuntimeException{
    public JwtNotFoundException() {
    }

    public JwtNotFoundException(String message) {
        super(message);
    }
}
