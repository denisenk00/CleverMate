package com.denysenko.apigateway.security.exceptions;

public class AssigningRoleException extends RuntimeException{
    public AssigningRoleException() {
    }

    public AssigningRoleException(String message) {
        super(message);
    }

    public AssigningRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssigningRoleException(Throwable cause) {
        super(cause);
    }
}
