package com.denysenko.messageservice.security;

import com.denysenko.messageservice.exceptions.JwtNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Auth0Service auth0Service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = JwtUtils.resolveJWTToken(request);
        if(!token.isPresent())
            throw new JwtNotFoundException("JWT wasn't resolved from request");
        var decodedJWT = this.auth0Service.validateUserToken(token.get());
        var authentication = JwtUtils.getAuthentication(decodedJWT);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }


}
