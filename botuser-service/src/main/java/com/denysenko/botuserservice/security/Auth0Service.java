package com.denysenko.botuserservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Auth0Service {
    @Value("${auth0.issuer-signing-key}")
    private String signingKey;
    @Value("${auth0.issuer}")
    private String issuer;
    @Value("${auth0.audience}")
    private String audience;

    public DecodedJWT validateUserToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(signingKey);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .withAudience(audience)
                    .build()
                    .verify(token);
        } catch (Exception e) {
            throw new JWTVerificationException("Token validation failed", e);
        }
    }
}
