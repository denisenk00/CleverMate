package com.denysenko.apigateway.security;

import com.denysenko.apigateway.security.auth0.Auth0Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class SecurityConfig {

    private final BasicAuthenticationManager basicAuthenticationManager;
    private final Auth0Service auth0Service;

    private final String[] WHITELIST = {
            "/auth/*",
            "/swagger/**",
            "/actuator",
            "/actuator/**"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                                .pathMatchers(WHITELIST).permitAll()
                                .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(basicAuthenticationManager)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .logout(ServerHttpSecurity.LogoutSpec::disable)
                .addFilterAt(new JwtAuthenticationFilter(auth0Service), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }


}
