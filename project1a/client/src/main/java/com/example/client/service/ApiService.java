package com.example.client.service;

import com.example.client.entity.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDto callServerApi() {
        String url = "http://localhost:8080/response";
        ResponseEntity<ResponseDto> response = restTemplate.getForEntity(url, ResponseDto.class);
        return response.getBody();
    }
}

