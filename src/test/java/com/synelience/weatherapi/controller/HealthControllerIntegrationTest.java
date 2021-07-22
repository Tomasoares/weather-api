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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();
    private String targetUrl;

    @BeforeEach
    public void init() {
        targetUrl = URLBuilder.getAddress(ResourceConstants.HEALTH, port);
    }

    @Test
    public void test_givenHealthEndpoint_whenGet_shouldReturnStatusOk() {
        ResponseEntity<Heartbeat> response
                = template.getForEntity(targetUrl, Heartbeat.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void test_givenHealthEndpoint_whenGet_shouldReturnHeartbeatImAlive() {
        ResponseEntity<Heartbeat> response
                = template.getForEntity(targetUrl, Heartbeat.class);

        assertEquals("I'm Alive!", response.getBody().getMessage());
    }
}
