package org.example;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubcriber {
    public static void main(String[] args) {
        String brokerUrl = "tcp://broker.hivemq.com:1883";
        String clientId = "JavaSubscriberClient";
        String topic = "test/mqttbox";

        try {
            // Tạo client MQTT
            MqttClient client = new MqttClient(brokerUrl, clientId);

            // Đặt callback để xử lý message nhận được
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Message received from topic " + topic + ": " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Không dùng trong subscribe
                }
            });

            // Kết nối và subscribe vào topic
            client.connect();
            client.subscribe(topic);
            System.out.println("Subscribed to topic: " + topic);

            // Chờ dữ liệu (để chương trình chạy không bị thoát)
            while (true) {
                // Để chương trình duy trì kết nối và lắng nghe message
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
