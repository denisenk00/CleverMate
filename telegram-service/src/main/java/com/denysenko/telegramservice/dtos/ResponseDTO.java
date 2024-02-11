package com.denysenko.telegramservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record ResponseDTO(@NotNull Long botUserId, @NotBlank String text) implements Serializable {
}
