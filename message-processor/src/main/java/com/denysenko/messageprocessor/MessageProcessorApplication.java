package com.denysenko.messageprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MessageProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageProcessorApplication.class, args);
    }

}
