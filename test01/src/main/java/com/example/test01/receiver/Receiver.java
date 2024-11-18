package com.example.test01.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.test01.model.SensorData;

@Component
public class Receiver {

    @RabbitListener(queues = "sensorQueue")
    public void receiveMessage(SensorData sensorData) {
        System.out.println("Received: " + sensorData);
    }
}
