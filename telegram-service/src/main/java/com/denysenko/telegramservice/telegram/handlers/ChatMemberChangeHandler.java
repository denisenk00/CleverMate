package com.denysenko.telegramservice.telegram.handlers;

import com.denysenko.telegramservice.clients.BotUserClient;
import com.denysenko.telegramservice.exceptions.UnhandledUpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class ChatMemberChangeHandler extends Handler{

    private final BotUserClient botUserClient;

    @Override
    public boolean isApplicable(Update update) {
        return update.hasMyChatMember();
    }

    @Override
    public void handle(Update update) {
        ChatMemberUpdated chatMemberUpdated = update.getMyChatMember();
        String newStatus = chatMemberUpdated.getNewChatMember().getStatus();
        if(newStatus.equals("kicked")) {
            Long chatId = chatMemberUpdated.getFrom().getId();
            botUserClient.patchBotUserStatusById(chatId, false);
        }else throw new UnhandledUpdateException(update);
    }
}
