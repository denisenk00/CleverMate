package com.denysenko.telegramservice.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public record RequestDTO(Long chatId, String text, LocalDateTime createdAt) implements Serializable {
}
