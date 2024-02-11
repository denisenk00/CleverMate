package com.denysenko.messageprocessor.rabbitmq;

import com.denysenko.messageprocessor.dtos.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendResponse(ResponseDTO responseDTO){
        rabbitTemplate.convertAndSend("message-direct", "responses", responseDTO);
    }
}
