package com.denysenko.apigateway;

import com.denysenko.apigateway.security.auth0.Auth0Service;
import com.denysenko.apigateway.security.dtos.CredentialsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Auth0Service auth0Service;
    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Mono<ResponseEntity> register(@RequestBody @Valid CredentialsDTO credentials){
        auth0Service.createUserWithAdminRole(credentials);
        String token = auth0Service.authenticateUser(credentials).getAccessToken();
        return Mono.just(ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body("Admin registered successfully"));
    }

    @PostMapping("/authenticate")
    public Mono<ResponseEntity> authenticate(@RequestBody @Valid CredentialsDTO credentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        String token = auth0Service.authenticateUser(credentials).getAccessToken();
        return Mono.just(ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body("Admin authorized successfully"));
    }

}
