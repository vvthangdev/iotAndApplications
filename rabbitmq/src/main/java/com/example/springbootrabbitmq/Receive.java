package com.example.springbootrabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

// Default Exchange (type=direct)
//public class Receive {
//    private final static String QUEUE_NAME = "hung5586";
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
//        });
//    }
//}

// Fanout
//public class Receive {
//    private static final String EXCHANGE_NAME = "hung5586";
//
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, EXCHANGE_NAME, "");
//
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
//    }
//}

// Direct Exchange
import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class Receive {
    private final static String EXCHANGE_NAME = "sensor_exchange";
    private final static String QUEUE_NAME = "vvthang";

    public static void main(String[] args) throws Exception {
        // Thiết lập kết nối đến RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");  // Hoặc địa chỉ IP của RabbitMQ
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Tạo exchange kiểu direct
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            // Tạo hàng đợi và liên kết với exchange qua routing key 'sensor_data'
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "sensor_data");

            System.out.println("Đang chờ nhận dữ liệu...");

            // Hàm xử lý khi nhận dữ liệu
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Đã nhận: " + message);
            };

            // Đăng ký callback cho hàng đợi
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        }
    }
}

