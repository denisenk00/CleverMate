package com.denysenko.botuserservice.exceptions;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }
}
