package com.denysenko.telegramservice.dtos;

import java.io.Serializable;

public record RequestDTO(Long botUserId, String text){
}
