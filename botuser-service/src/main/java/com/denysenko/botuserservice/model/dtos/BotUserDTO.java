package com.denysenko.botuserservice.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BotUserDTO (@NotNull Long id, @NotBlank String name, boolean active){}

