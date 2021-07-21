package com.synelience.weatherapi.openweather.endpoint;

import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class WeatherEndpointTest {

    @Autowired
    private WeatherEndpoint service;

    @Test
    public void givenFlorianopolisCity_whenCallCurrentWeatherEndpoint_shouldReturnValidResponse() {
        String city = "Florianopolis";
        ResponseEntity<String> response = this.service.callCurrentWeatherEndpoint(city);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
