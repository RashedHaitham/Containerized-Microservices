package com.example.enterBook.service;

import com.example.enterBook.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private static final String AUTH_SERVICE_URL = "http://host.docker.internal:8081/auth";

    public ResponseEntity<String> authenticate(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        try {
            return restTemplate.postForEntity(AUTH_SERVICE_URL + "/login", request, String.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            return new ResponseEntity<>(e.getResponseBodyAsString(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean validateToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(token, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(AUTH_SERVICE_URL + "/validate-token", entity, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            System.out.println("Failed to validate token with AuthenticationService.");
            e.printStackTrace();
            return false;
        }
    }
}