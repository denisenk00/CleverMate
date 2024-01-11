package com.denysenko.telegramservice;

import com.denysenko.telegramservice.exceptions.TelegramSendFailure;
import com.denysenko.telegramservice.exceptions.UnhandledUpdateException;
import com.denysenko.telegramservice.telegram.TelegramAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final TelegramAPIService telegramAPIService;

    @ExceptionHandler(value = UnhandledUpdateException.class)
    public void unhandledTelegramUpdate(UnhandledUpdateException unhandledUpdateException){
        telegramAPIService.sendMessage(unhandledUpdateException.getChatId(), "Your update can't be handled properly, try again");
    }

    @ExceptionHandler(value = TelegramSendFailure.class)
    public void telegramSendFailure(TelegramSendFailure telegramSendFailure){
        //log.err(telegramSendFailure.getCause()); --
    }

    @ExceptionHandler(value = Exception.class)
    public void unhandledTelegramUpdate(Exception exception){
        //log...
    }

}
