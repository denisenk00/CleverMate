package com.denysenko.apigateway.security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialsDTO{
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
