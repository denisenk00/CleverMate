package com.denysenko.telegramservice.dtos;

import java.io.Serializable;

public record ResponseDTO(Long chatId, String text) implements Serializable {
}
