package com.denysenko.messageservice.rabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setObservationEnabled(true);
        return rabbitTemplate;
    }

}
