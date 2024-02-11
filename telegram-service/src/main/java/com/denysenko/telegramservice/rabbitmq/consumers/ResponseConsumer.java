package com.denysenko.telegramservice.rabbitmq.consumers;

import com.denysenko.telegramservice.dtos.ResponseDTO;
import com.denysenko.telegramservice.telegram.TelegramAPIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ResponseConsumer {

    private final TelegramAPIService telegramAPIService;

    @RabbitListener(queues = "responses")
    public void processResponse(@Valid ResponseDTO response){
        telegramAPIService.sendMessage(response.botUserId(), response.text());
    }

}
