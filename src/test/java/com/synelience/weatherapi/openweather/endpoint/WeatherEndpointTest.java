package com.synelience.weatherapi.openweather.endpoint;

import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@SpringBootTest
//@Disabled
public class WeatherEndpointTest {

    @Autowired
    private WeatherEndpoint endpoint;

    @Autowired
    private OpenWeatherConfiguration cfg;

    @Test
    public void givenFlorianopolisCity_whenCallCurrentWeatherEndpoint_shouldReturnValidResponse() {
        String city = "Florianopolis";
        String apiKey = cfg.getApiKey();
        String units = "metric";

        ResponseEntity<String> response = this.endpoint.callCurrentWeatherEndpoint(city, apiKey, units);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenFlorianopolisCity_whenCallCurrentWeatherEndpoint_whenNoApiKey_shouldReturnUnauthorized() {
        String city = "Florianopolis";
        String apiKey = null;
        String units = "metric";

        ResponseEntity<String> response = this.endpoint.callCurrentWeatherEndpoint(city, apiKey, units);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

}
