package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Scanner;

public class MqttPublisher {
    public static void main(String[] args) {
        // MQTT Broker URL và topic
        String brokerUrl = "tcp://broker.hivemq.com:1883"; // Broker công khai
        String clientId = MqttClient.generateClientId();   // Tạo Client ID duy nhất
        String topic = "test/mqttbox";

        Scanner scanner = new Scanner(System.in);

        try {
            // Tạo MQTT client và thiết lập tùy chọn kết nối
            MqttClient client = new MqttClient(brokerUrl, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true); // Kết nối không giữ trạng thái trước đó

            // Kết nối tới broker
            System.out.println("Connecting to broker: " + brokerUrl);
            client.connect(options);
            System.out.println("Connected successfully!");

            // Vòng lặp gửi tin nhắn
            while (true) {
                System.out.println("Nhập JSON để gửi (hoặc gõ 'exit' để thoát):");
                String input = scanner.nextLine();

                // Kiểm tra nếu người dùng muốn thoát
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Tạo và gửi tin nhắn MQTT
                MqttMessage message = new MqttMessage(input.getBytes());
                message.setQos(1); // QoS: Đảm bảo gửi ít nhất một lần
                client.publish(topic, message);
                System.out.println("Published message: " + input);
            }

            // Ngắt kết nối và đóng client
            client.disconnect();
            System.out.println("Disconnected from broker.");
            client.close();
        } catch (MqttException e) {
            System.err.println("Error while publishing message: " + e.getReasonCode());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
