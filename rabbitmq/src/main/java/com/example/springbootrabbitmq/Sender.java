package com.example.springbootrabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

//Direct Exchange
public class Sender {
    private final static String EXCHANGE_NAME = "sensor_exchange";

    public static void main(String[] args) throws Exception {
        // Thiết lập kết nối đến RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");  // Hoặc địa chỉ IP của RabbitMQ
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Tạo exchange kiểu direct
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            JSONObject data = new JSONObject();
            data.put("id", 11);
            data.put("packet_no", 126);
            data.put("temperature", 30);
            data.put("humidity", 60);
            data.put("tds", 1100);
            data.put("pH", 5.0);

            // Đóng gói dữ liệu thành chuỗi JSON
            String message = data.toString();

            // Gửi dữ liệu với routing key 'sensor_data'
            channel.basicPublish(EXCHANGE_NAME, "sensor_data", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Đã gửi: " + message);
        }
    }
}
