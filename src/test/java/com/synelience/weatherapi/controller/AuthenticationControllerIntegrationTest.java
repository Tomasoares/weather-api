package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.model.Heartbeat;
import com.synelience.weatherapi.util.URLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();
    private String targetUrl;

    @BeforeEach
    public void init() {
        this.targetUrl = URLBuilder.getAddress(ResourceConstants.AUTHENTICATION, port);
    }

    @Test
    public void givenValidCredentials_whenGetApiToken_shouldReturnStatusOk() {
        ResponseEntity<String> response = template.withBasicAuth("synelience", "synelience")
                .getForEntity(targetUrl, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenValidCredentials_whenGetApiToken_shouldReturnApiToken() {
        ResponseEntity<String> response = template.withBasicAuth("synelience", "synelience")
                .getForEntity(targetUrl, String.class);

        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }
}
