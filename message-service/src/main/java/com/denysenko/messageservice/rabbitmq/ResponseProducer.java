package com.denysenko.messageservice.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendResponse(ResponseDTO responseDTO){
        rabbitTemplate.convertAndSend("message-direct", "responses", responseDTO);
    }
}
