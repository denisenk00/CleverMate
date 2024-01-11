package com.denysenko.telegramservice.rabbitmq.producers;

import com.denysenko.telegramservice.dtos.RequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendRequest(RequestDTO requestDTO){
        rabbitTemplate.convertAndSend("message-direct", "requests", requestDTO);
    }
}
