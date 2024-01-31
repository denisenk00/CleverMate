package com.denysenko.messageprocessor.clients;

import com.denysenko.messageprocessor.dtos.MessageDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("message-service")
@CircuitBreaker(name = "message-service")
@Retry(name = "message-service")
@Bulkhead(name = "message-service")
public interface MessageClient {

    @PostMapping("/private/messages")
    void saveMessage(MessageDTO messageDTO);
}
