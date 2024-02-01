package com.denysenko.telegramservice.clients;

import com.denysenko.telegramservice.dtos.BotUserDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "botuser-service")
@CircuitBreaker(name = "botuser-service")
@Retry(name = "botuser-service")
@Bulkhead(name = "botuser-service")
public interface BotUserClient {

    @PutMapping("/private/botusers/{id}/status")
    void patchBotUserStatusById(@PathVariable Long id, @RequestBody boolean isActive);

    @PutMapping("/private/botusers")
    BotUserDTO  putBotUser(@RequestBody BotUserDTO botUserDTO);
}
