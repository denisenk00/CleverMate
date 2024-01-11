package com.denysenko.telegramservice.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue requestQueue(){
        return new Queue("requests", true, true, false);
    }

    @Bean
    public Queue responseQueue(){
        return new Queue("responses", true, true, false);
    }

    @Bean
    public DirectExchange messageExchange(){
        return new DirectExchange("message-direct");
    }

    @Bean
    public Binding requestQueueExchangeBinding(){
        return BindingBuilder
                .bind(requestQueue())
                .to(messageExchange())
                .with("requests");
    }

    @Bean
    public Binding responseQueueExchangeBinding(){
        return BindingBuilder
                .bind(responseQueue())
                .to(messageExchange())
                .with("responses");
    }



}
