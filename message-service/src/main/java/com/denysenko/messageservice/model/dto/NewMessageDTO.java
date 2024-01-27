package com.denysenko.messageservice.model.dto;

import com.denysenko.messageservice.model.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewMessageDTO (@NotNull Long botUserId, @NotBlank String text, @NotNull MessageType type){
}
