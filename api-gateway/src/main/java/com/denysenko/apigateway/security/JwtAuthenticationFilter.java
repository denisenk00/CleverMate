package com.denysenko.apigateway.security;

import com.denysenko.apigateway.security.auth0.Auth0Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final Auth0Service auth0Service;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Optional<String> token = JwtUtils.resolveJWTToken(exchange.getRequest());
        if (token.isPresent()) {
            var decodedJWT = this.auth0Service.validateUserToken(token.get());
            return Mono.fromCallable(() -> JwtUtils.getAuthentication(decodedJWT))
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(authentication ->
                        chain.filter(exchange)
                                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
                    );
        }
        return chain.filter(exchange);
    }


}
