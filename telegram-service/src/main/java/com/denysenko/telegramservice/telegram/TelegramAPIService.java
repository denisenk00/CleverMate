package com.denysenko.telegramservice.telegram;

import com.denysenko.telegramservice.exceptions.TelegramSendFailure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramAPIService extends DefaultAbsSender {
    @Value("${telegram.bot.token}")
    private String botToken;

    protected TelegramAPIService() {
        super(new DefaultBotOptions());
    }

    public void sendMessage(Long chatId, String text){
        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text.trim());
        try {
            execute(messageBuilder.build());
        } catch (TelegramApiException e) {
            throw new TelegramSendFailure("Failed sending message to chatId = " + chatId, e);
        }
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
