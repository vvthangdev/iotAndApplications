package com.example.test01.sender;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class SenderConfig {

    @Bean
    public Queue senderQueue() {
        return new Queue("sensorQueue");
    }

    @Bean
    public DirectExchange sensorExchange() {
        // Tạo một direct exchange có tên 'sensorExchange'
        return new DirectExchange("sensorExchange");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // Đặt Jackson2JsonMessageConverter cho RabbitTemplate
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
