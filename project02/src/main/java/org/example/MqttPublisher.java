import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Scanner;

public class MqttPublisher {
    public static void main(String[] args) {
        String brokerUrl = "tcp://broker.hivemq.com:1883";
        String clientId = "JavaPublisherClient";
        String topic = "test/mqttbox";
        Scanner scanner = new Scanner(System.in);

        try {
            // Tạo client MQTT và kết nối
            MqttClient client = new MqttClient(brokerUrl, clientId);
            client.connect();

            while (true) {
                // Nhập chuỗi JSON từ người dùng
                System.out.println("Nhập JSON để gửi (hoặc gõ 'exit' để thoát):");
                String input = scanner.nextLine();

                // Kiểm tra nếu người dùng muốn thoát
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Tạo một tin nhắn MQTT với JSON đầu vào
                MqttMessage message = new MqttMessage();
                message.setPayload(input.getBytes());  // Sử dụng chuỗi JSON trực tiếp
                message.setQos(1);  // Chọn mức QoS, có thể điều chỉnh

                // Publish tin nhắn lên topic đã định
                client.publish(topic, message);
                System.out.println("Published message: " + input);
            }

            // Ngắt kết nối khi hoàn thành
            client.disconnect();
            client.close();
            System.out.println("Đã ngắt kết nối khỏi MQTT broker.");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
