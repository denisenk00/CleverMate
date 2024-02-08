package com.denysenko.botuserservice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.denysenko.botuserservice.exceptions.DocumentNotFoundException;
import com.denysenko.botuserservice.exceptions.JwtNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity handleDocumentNotFoundException(DocumentNotFoundException documentNotFoundException){
        return new ResponseEntity(documentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({JwtNotFoundException.class, JWTVerificationException.class})
    public ResponseEntity handleUnauthorized(Exception e){
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleInternalServerError(Exception e){
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
