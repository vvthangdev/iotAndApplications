package com.example.demo.controller;

import com.example.demo.entity.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoController {

    @GetMapping("/response")
    public ResponseEntity<ResponseDto> helloWorld() {
        ResponseDto responseDto = new ResponseDto();

        // Khoi toa noi dung
        responseDto.setDeviceId(generateRandomString(15));
        responseDto.setQrCodeId(generateRandomString(25));
        responseDto.setQrCodeValue(generateRandomString(20));

        return ResponseEntity.ok()
                .body(responseDto);
    }

    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
