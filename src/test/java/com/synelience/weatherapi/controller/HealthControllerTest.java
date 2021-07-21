package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.model.Heartbeat;
import com.synelience.weatherapi.util.URLBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate template = new TestRestTemplate();

    @Test
    public void test_givenHealthEndpoint_whenGet_shouldReturnStatusOk() {
        String url = URLBuilder.getAddress(ResourceConstants.HEALTH, port);

        ResponseEntity<Heartbeat> response
                = template.getForEntity(url, Heartbeat.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void test_givenHealthEndpoint_whenGet_shouldReturnHeartbeatImAlive() {
        String url = URLBuilder.getAddress(ResourceConstants.HEALTH, port);

        ResponseEntity<Heartbeat> response
                = template.getForEntity(url, Heartbeat.class);

        assertEquals("I'm Alive!", response.getBody().getMessage());
    }
}
