package com.denysenko.telegramservice.exceptions;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class UnhandledUpdateException extends RuntimeException{
    private Long chatId;
    private Update update;
    private static String DEFAULT_MESSAGE = "Update from telegram api wasn't handled";

    public UnhandledUpdateException(String message, Update update) {
        super(message);
        this.chatId = update.getMyChatMember().getChat().getId();
        this.update = update;
    }

    public UnhandledUpdateException(Update update) {
        super(DEFAULT_MESSAGE);
        this.chatId = update.getMyChatMember().getChat().getId();
        this.update = update;
    }
}
