package com.denysenko.messageservice.controllers;

import com.denysenko.messageservice.model.dto.AdminMessageDTO;
import com.denysenko.messageservice.model.Message;
import com.denysenko.messageservice.services.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class PublicMessageController {

    private final MessageService messageService;

    @GetMapping
    List<Message> getLastNMessagesByBotUserId(
            @RequestParam(name = "userId") long botUserId,
            @RequestParam(name = "amount", defaultValue = "10", required = false) int amount){
        return messageService.getLastNMessagesByBotUserId(botUserId, amount);
    }

    @GetMapping("/page")
    Page<Message> getPageOfMessages(
            @RequestParam(name = "userId") long botUserId,
            @RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(name = "size", defaultValue = "30", required = false) int pageSize){
        return messageService.getPageOfMessages(botUserId, pageNumber, pageSize);
    }

    //todo: implement assigning admin info
    @PostMapping
    Message postAdminMessage(@RequestBody @Valid AdminMessageDTO adminMessageDTO){
        throw new NotImplementedException();
        //@AuthenticationPrincipal AuthenticatedUser authenticatedUser){
        //return messageService.sendToTGAndSave(adminMessageDTO, null);
    }
}
