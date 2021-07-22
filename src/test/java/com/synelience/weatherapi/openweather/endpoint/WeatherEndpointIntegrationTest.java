package com.synelience.weatherapi.openweather.endpoint;

import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@SpringBootTest
// @Disabled
public class WeatherEndpointIntegrationTest {

    @Autowired
    private WeatherEndpoint endpoint;

    @Autowired
    private OpenWeatherConfiguration cfg;

    @Test
    public void givenFlorianopolisCity_whenCallCurrentWeatherEndpoint_shouldReturnValidResponse() {
        String city = "Florianopolis";
        String apiKey = cfg.getApiKey();
        String units = "metric";

        ResponseEntity<CurrentWeather> response = this.endpoint.callCurrentWeatherEndpoint(city, apiKey, units);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenInvalidCity_whenCallCurrentWeatherEndpoint_shouldReturnNotFound() {
        String city = "abc";
        String apiKey = cfg.getApiKey();
        String units = "metric";

        ResponseEntity<CurrentWeather> response = this.endpoint.callCurrentWeatherEndpoint(city, apiKey, units);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
