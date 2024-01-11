package com.denysenko.telegramservice.rabbitmq.consumers;

import com.denysenko.telegramservice.dtos.ResponseDTO;
import com.denysenko.telegramservice.telegram.TelegramAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RequiredArgsConstructor
public class ResponseConsumer {

    private final TelegramAPIService telegramAPIService;

    @RabbitListener(queues = "responses")
    public void processResponse(ResponseDTO response){
        telegramAPIService.sendMessage(response.chatId(), response.text());
    }

}
