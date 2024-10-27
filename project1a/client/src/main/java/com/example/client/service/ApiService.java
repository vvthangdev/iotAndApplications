package com.example.client.service;

import com.example.client.entity.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class ApiService {


    // Thay bang dia chi IP cua server
    private static final String IP_ADDRESS = "192.168.1.xxx";
    private static final String PORT = "8080";
    private static final String SERVER_URL = "http://" + IP_ADDRESS + ":" + PORT + "/response";

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDto callServerApi() {
        String url = "http://localhost:8080/response";
        ResponseEntity<ResponseDto> response = restTemplate.getForEntity(url, ResponseDto.class);
        System.out.println("Connecting to server at: " + SERVER_URL);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            ResponseDto data = response.getBody();
            System.out.println("Received data:");
            System.out.println("Device ID: " + data.getDeviceId());
            System.out.println("QR Code ID: " + data.getQrCodeId());
            System.out.println("QR Code Value: " + data.getQrCodeValue());
        } else {
            System.out.println("Error calling API: " + response.getStatusCode());
        }
        return response.getBody();
    }
}

