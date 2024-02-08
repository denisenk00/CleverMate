package com.denysenko.apigateway;

import com.denysenko.apigateway.security.exceptions.AssigningRoleException;
import com.denysenko.apigateway.security.exceptions.AuthenticationException;
import com.denysenko.apigateway.security.exceptions.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public Mono<ResponseEntity> handleAuthenticationException(AuthenticationException ex){
        return Mono.just(new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(UserCreationException.class)
    public Mono<ResponseEntity> handleUserCreationException(UserCreationException ex){
        return Mono.just(new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler({RuntimeException.class, AssigningRoleException.class})
    public Mono<ResponseEntity> handleException(RuntimeException ex){
        return Mono.just(new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
