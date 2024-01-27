package com.denysenko.telegramservice.dtos;

import java.io.Serializable;

public record ResponseDTO(Long botUserId, String text) implements Serializable {
}
