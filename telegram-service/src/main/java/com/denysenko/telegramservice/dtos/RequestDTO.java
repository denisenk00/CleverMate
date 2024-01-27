package com.denysenko.telegramservice.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public record RequestDTO(Long botUserId, String text, LocalDateTime time) implements Serializable {
}
