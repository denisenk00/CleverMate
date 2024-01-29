package com.denysenko.messageprocessor.clients;

import com.denysenko.messageprocessor.dtos.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("message-service")
public interface MessageClient {

    @PostMapping("/private/messages")
    void saveMessage(MessageDTO messageDTO);
}
