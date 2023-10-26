package com.amdck.phonepe.pg;

import org.springframework.http.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class AppService {

    RestTemplate restTemplate = new RestTemplate();
    public void sendMessage(String botToken, long chatId, String text, String parseMode) throws IOException {
        try {
            String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("chat_id", String.valueOf(chatId));
            body.add("text", text);
            body.add("parse_mode", parseMode);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
            } else {
                System.err.println("API Request failed with HTTP error code: " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            System.out.println("error caugth in send message method");
            e.printStackTrace();
        }
    }

}

