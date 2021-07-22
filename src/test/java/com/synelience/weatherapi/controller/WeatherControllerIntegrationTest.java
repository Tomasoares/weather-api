package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.manager.api.WeatherManager;
import com.synelience.weatherapi.model.Heartbeat;
import com.synelience.weatherapi.openweather.endpoint.WeatherEndpointImpl;
import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import com.synelience.weatherapi.util.URLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();
    private String targetUrl;

    @MockBean
    private WeatherEndpoint endpoint;

    @BeforeEach
    public void init() {
        targetUrl = URLBuilder.getAddress(ResourceConstants.WEATHER, port);
    }

    @Test
    public void test_givenHealthEndpoint_whenGet_shouldReturnStatusOk() {
        when(endpoint.callCurrentWeatherEndpoint(any(),any(),any()))
                .thenReturn(ResponseEntity.ok(new CurrentWeather()));

        String city = "aaa";
        String url = String.join("", targetUrl, "?city=", city);

        ResponseEntity<String> response
                = template.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
