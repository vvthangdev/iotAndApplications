package com.example.test01.sender;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.test01.model.SensorData;

@SpringBootApplication
public class SenderApplication implements CommandLineRunner {

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Dữ liệu mẫu để gửi đi
        SensorData data = new SensorData();
        data.setId(11);
        data.setPacketNo(126);
        data.setTemperature(30);
        data.setHumidity(60);
        data.setTds(1100);
        data.setpH(5.0);

        // Gửi dữ liệu qua RabbitMQ
        sender.sendMessage(data);
    }
}
