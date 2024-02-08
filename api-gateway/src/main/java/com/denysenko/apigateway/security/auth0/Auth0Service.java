package com.denysenko.apigateway.security.auth0;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.denysenko.apigateway.security.dtos.CredentialsDTO;
import com.denysenko.apigateway.security.exceptions.AssigningRoleException;
import com.denysenko.apigateway.security.exceptions.AuthenticationException;
import com.denysenko.apigateway.security.exceptions.UserCreationException;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Auth0Service {
    @Value("${auth0.application.domain}")
    private String domain;
    @Value("${auth0.application.client-id}")
    private String clientId;
    @Value("${auth0.application.client-secret}")
    private String clientSecret;
    @Value("${auth0.client-api.audience}")
    private String audience;
    @Value("${auth0.client-api.issuer-signing-key}")
    private String signingKey;
    @Value("${auth0.users.admin-role-id}")
    private String adminRoleId;

    private AuthAPI authAPI;
    private ManagementAPI managementAPI;

    @PostConstruct
    public void init(){
        authAPI = new AuthAPI(domain, clientId, clientSecret);
        String token = getManagementApiToken();
        managementAPI = new ManagementAPI(domain, token);
    }

    @SneakyThrows
    public String getManagementApiToken() {
        var request = authAPI.requestToken("https://" + domain + "/api/v2/");
        var tokenHolder = request.execute();
        return tokenHolder.getAccessToken();
    }


    public void createUserWithAdminRole(CredentialsDTO credentialsDTO) {
        String USERNAME_PASS_CONNECTION_TYPE = "Username-Password-Authentication";
        var user = new User();
        user.setConnection(USERNAME_PASS_CONNECTION_TYPE);
        user.setEmail(credentialsDTO.getEmail());
        user.setPassword(credentialsDTO.getPassword());
        String userId = createUser(user);
        assignRoleToUser(userId, adminRoleId);
    }

    private void assignRoleToUser(String userId, String roleId){
        try {
            managementAPI.users().addRoles(userId, List.of(roleId)).execute();
        } catch (com.auth0.exception.Auth0Exception e){
            throw new AssigningRoleException(e);
        }
    }

    private String createUser(User user){
        try {
            return managementAPI.users().create(user).execute().getId();
        } catch (com.auth0.exception.Auth0Exception e){
            throw new UserCreationException(e);
        }
    }

    public TokenHolder authenticateUser(CredentialsDTO credentialsDTO) {
        try {
            return authAPI.login(credentialsDTO.getEmail(), credentialsDTO.getPassword())
                    .setAudience(audience)
                    .execute();
        } catch (com.auth0.exception.Auth0Exception e){
            throw new AuthenticationException(e);
        }
    }

    public DecodedJWT validateUserToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(signingKey);
            String issuer = "https://" + domain + "/";
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
