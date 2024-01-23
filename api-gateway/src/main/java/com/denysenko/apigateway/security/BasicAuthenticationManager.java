package com.denysenko.apigateway.security;

import com.auth0.jwt.JWT;
import com.denysenko.apigateway.security.auth0.Auth0Service;
import com.denysenko.apigateway.CredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class BasicAuthenticationManager implements ReactiveAuthenticationManager {

    private final Auth0Service auth0Service;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        Authentication authenticated = null;
        if(authentication instanceof UsernamePasswordAuthenticationToken usPasswordToken){
            String email = (String) usPasswordToken.getPrincipal();
            String password = (String) usPasswordToken.getCredentials();
            var tokenHolder = auth0Service.authenticateUser(new CredentialsDTO(email, password));
            var accessToken = JWT.decode(tokenHolder.getAccessToken());
            authenticated = JwtUtils.getAuthentication(accessToken);
        }
        Objects.requireNonNull(authenticated, "Authentication method not supported");
        return Mono.just(authenticated);
    }

}
