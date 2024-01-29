package com.denysenko.messageprocessor.dtos;

public record MessageDTO(Long botUserId, String text, MessageType type){
}
