package com.example.client.controller;

import com.example.client.entity.ResponseDto;
import com.example.client.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final ApiService apiService;

    public ClientController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/call-server")
    public ResponseDto callServer() {
        return apiService.callServerApi();
    }
}
