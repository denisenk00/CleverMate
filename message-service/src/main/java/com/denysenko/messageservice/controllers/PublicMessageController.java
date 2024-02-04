package com.denysenko.messageservice.controllers;

import com.denysenko.messageservice.model.dto.AdminMessageDTO;
import com.denysenko.messageservice.model.Message;
import com.denysenko.messageservice.services.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class PublicMessageController {

    private final MessageService messageService;

    @GetMapping
    @PreAuthorize("hasAuthority('read:messages')")
    List<Message> getLastNMessagesByBotUserId(
            @RequestParam(name = "userId") long botUserId,
            @RequestParam(name = "amount", defaultValue = "10", required = false) int amount){
        return messageService.getLastNMessagesByBotUserId(botUserId, amount);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('read:messages')")
    Page<Message> getPageOfMessages(
            @RequestParam(name = "userId") long botUserId,
            @RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(name = "size", defaultValue = "30", required = false) int pageSize){
        return messageService.getPageOfMessages(botUserId, pageNumber, pageSize);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create:messages')")
    //NOTE!!! username is parsed from JWT is identifier of Auth0 user like 'Auth0|fsdkfjdjfsjfdskf'
    Message postAdminMessage(@RequestBody @Valid AdminMessageDTO adminMessageDTO){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return messageService.sendToTGAndSave(adminMessageDTO, username);
    }
}
