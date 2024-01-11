package com.denysenko.telegramservice.exceptions;

public class TelegramSendFailure extends RuntimeException{
    public TelegramSendFailure(String message, Throwable cause) {
        super(message, cause);
    }
}
