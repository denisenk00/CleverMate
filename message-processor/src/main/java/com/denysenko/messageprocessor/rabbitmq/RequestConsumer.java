package com.denysenko.messageprocessor.rabbitmq;

import com.denysenko.messageprocessor.clients.MessageClient;
import com.denysenko.messageprocessor.dtos.MessageDTO;
import com.denysenko.messageprocessor.dtos.MessageType;
import com.denysenko.messageprocessor.dtos.RequestDTO;
import com.denysenko.messageprocessor.dtos.ResponseDTO;
import com.denysenko.messageprocessor.services.OpenAIService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RequestConsumer {

    private final OpenAIService openAIService;
    private final MessageClient messageClient;
    private final ResponseProducer responseProducer;

    @RabbitListener(queues = "requests")
    public void processRequest(@Valid RequestDTO requestDTO) {
        System.out.println("processing request");
        var botUserId = requestDTO.botUserId();
        var requestText = requestDTO.text();
        messageClient.saveMessage(new MessageDTO(botUserId, requestText, MessageType.REQUEST));
        String responseText = openAIService.executeTextRequest(requestText);
        messageClient.saveMessage(new MessageDTO(botUserId, responseText, MessageType.RESPONSE));
        responseProducer.sendResponse(new ResponseDTO(botUserId, responseText));
    }

}
