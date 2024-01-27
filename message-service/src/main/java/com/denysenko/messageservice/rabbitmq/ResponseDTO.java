package com.denysenko.messageservice.rabbitmq;

import java.io.Serializable;

public record ResponseDTO(Long botUserId, String text) implements Serializable {
}
