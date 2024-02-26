package com.denysenko.telegramservice.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue responseQueue(){
        return new Queue("responses", true, true, false);
    }

    @Bean
    public DirectExchange messageExchange(){
        return new DirectExchange("message-direct");
    }

    @Bean
    public Binding responseQueueExchangeBinding(){
        return BindingBuilder
                .bind(responseQueue())
                .to(messageExchange())
                .with("responses");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setObservationEnabled(true);
        return rabbitTemplate;
    }

}
