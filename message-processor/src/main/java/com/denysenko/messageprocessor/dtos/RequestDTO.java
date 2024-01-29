package com.denysenko.messageprocessor.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestDTO(@NotNull Long botUserId, @NotBlank String text) {
}
