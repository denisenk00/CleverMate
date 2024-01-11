package com.denysenko.telegramservice.telegram.handlers;

import com.denysenko.telegramservice.exceptions.UnhandledUpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;


@RequiredArgsConstructor
@Component
public class GlobalUpdateHandler {

    private final Set<Handler> handlers;

    public void handle(Update update) {
        handlers.stream()
                .filter(handler -> handler.isApplicable(update))
                .findFirst()
                .ifPresentOrElse(
                        handler -> handler.handle(update),
                        () -> new UnhandledUpdateException(update)
                );
    }

}
