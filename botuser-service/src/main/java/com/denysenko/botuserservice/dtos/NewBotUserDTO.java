package com.denysenko.botuserservice.dtos;

public record NewBotUserDTO(Long chatId, String name, boolean isActive) {
}
