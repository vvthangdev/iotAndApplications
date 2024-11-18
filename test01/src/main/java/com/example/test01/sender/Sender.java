package com.example.test01.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.test01.model.SensorData;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(SensorData data) {
        rabbitTemplate.convertAndSend("sensorExchange", "sensorRoutingKey", data);
        System.out.println("Sent: " + data);
    }
}
