package com.denysenko.botuserservice.controllers;

import com.denysenko.botuserservice.model.dtos.BotUserDTO;
import com.denysenko.botuserservice.services.BotUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/botusers")
@RequiredArgsConstructor
public class PrivateBotUserController {

    @Autowired
    private BotUserService botUserService;

    @PutMapping("/{id}/status")
    public void patchBotUserStatusByChatId(@PathVariable Long id, @RequestBody boolean isActive){
        botUserService.changeProfileStatusById(id, isActive);
    }

    @PutMapping
    public BotUserDTO putBotUser(@RequestBody @Validated BotUserDTO botUser){
        return botUserService.saveOrUpdate(botUser);
    }

}
