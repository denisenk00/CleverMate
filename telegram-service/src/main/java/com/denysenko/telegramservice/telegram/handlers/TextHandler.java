package com.denysenko.telegramservice.telegram.handlers;

import com.denysenko.telegramservice.dtos.RequestDTO;
import com.denysenko.telegramservice.exceptions.UnhandledUpdateException;
import com.denysenko.telegramservice.rabbitmq.producers.RequestProducer;
import com.denysenko.telegramservice.telegram.TelegramAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TextHandler extends Handler{

    private static final String START_COMMAND = "/start";
    private static final String START_MESSAGE = "Hi! Glad to see yoo here. I'm waiting for your first request)";
    //private final BotUserClient botUserClient;
    private final TelegramAPIService telegramAPIService;
    private final RequestProducer requestProducer;

    @Override
    public boolean isApplicable(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();

        if(text.equals(START_COMMAND)){
            telegramAPIService.sendMessage(chatId, START_MESSAGE);
            String username = message.getFrom().getUserName();
            //botUserClient.putBotUser(new BotUserDTO(chatId, username, true));
        }else {
            requestProducer.sendRequest(new RequestDTO(chatId, text, LocalDateTime.now()));
        }

    }
}
