package com.denysenko.messageservice.controllers;

import com.denysenko.messageservice.model.Message;
import com.denysenko.messageservice.model.dto.NewMessageDTO;
import com.denysenko.messageservice.services.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/messages")
@RequiredArgsConstructor
public class PrivateMessageController {

    private final MessageService messageService;

    @PostMapping
    public Message saveMessage(@Valid @RequestBody NewMessageDTO message){
        return messageService.save(message);
    }

}
