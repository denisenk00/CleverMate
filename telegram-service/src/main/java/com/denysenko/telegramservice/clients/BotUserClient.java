package com.denysenko.telegramservice.clients;

import com.denysenko.telegramservice.dtos.BotUserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "botUser")
public interface BotUserClient {

    //@PatchMapping("/botUsers/{}/status")
    boolean patchBotUserStatusById(@PathVariable Long botUserId, @RequestBody boolean isActive);

   // @PutMapping("/botUsers")
    BotUserDTO putBotUser(@RequestBody BotUserDTO botUserDTO);
}
