package com.example.test01.receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class ReceiverConfig {

    @Bean
    public Queue receiverQueue() {
        return new Queue("sensorQueue");
    }

    @Bean
    public DirectExchange sensorExchange() {
        return new DirectExchange("sensorExchange");
    }

    @Bean
    public Binding binding(Queue receiverQueue, DirectExchange sensorExchange) {
        // Tạo binding giữa queue và exchange
        return BindingBuilder.bind(receiverQueue).to(sensorExchange).with("sensorRoutingKey");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // Đặt Jackson2JsonMessageConverter cho RabbitTemplate
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
