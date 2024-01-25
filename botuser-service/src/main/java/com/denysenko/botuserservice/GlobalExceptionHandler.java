package com.denysenko.botuserservice;

import com.denysenko.botuserservice.exceptions.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity handleDocumentNotFoundException(DocumentNotFoundException documentNotFoundException){
        return new ResponseEntity(documentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
