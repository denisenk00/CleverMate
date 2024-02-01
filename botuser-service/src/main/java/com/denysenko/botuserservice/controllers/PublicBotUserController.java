package com.denysenko.botuserservice.controllers;

import com.denysenko.botuserservice.services.BotUserService;
import com.denysenko.botuserservice.model.dtos.BotUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/botusers")
@RequiredArgsConstructor
public class PublicBotUserController {

    @Autowired
    private BotUserService botUserService;

    @GetMapping
    public Page<BotUserDTO> getPageOfBotUsers(@RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber,
                                              @RequestParam(name = "size", defaultValue = "30", required = false) int pageSize){
        return botUserService.getPageOfBotUsers(pageNumber, pageSize);
    }

}
