package com.denysenko.botuserservice.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JwtUtils {

    private static final String AUTHORITIES_KEY = "permissions";
    public static final String HEADER_PREFIX = "Bearer ";

    public static Authentication getAuthentication(DecodedJWT accessToken) {
        Objects.requireNonNull(accessToken);
        var authorities = resolveUserPermissions(accessToken);
        var principal = new User(accessToken.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
    }

    private static List<? extends GrantedAuthority> resolveUserPermissions(DecodedJWT accessToken){
        Objects.requireNonNull(accessToken);
        var authoritiesClaim = accessToken.getClaim(AUTHORITIES_KEY);
        return authoritiesClaim == null
                ? AuthorityUtils.NO_AUTHORITIES
                : authoritiesClaim.asList(SimpleGrantedAuthority.class);

    }

    public static Optional<String> resolveJWTToken(HttpServletRequest request) {
        Objects.requireNonNull(request);
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

}
