package com.denysenko.telegramservice.configs;


import com.denysenko.telegramservice.telegram.handlers.GlobalUpdateHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class TelegramAPIConfig extends TelegramLongPollingBot {

    public static final String START_FAILED_MESSAGE = "Failed to register bot(check internet connection /" +
            " bot token or make sure only one instance of bot is running).";

    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;

    private final GlobalUpdateHandler globalUpdateHandler;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @PostConstruct
    private void startBot(){
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(START_FAILED_MESSAGE, e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        globalUpdateHandler.handle(update);
    }

}
