package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.controller.api.WeatherController;
import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.manager.api.WeatherManager;
import com.synelience.weatherapi.model.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    private WeatherController controller;

    @Mock
    private WeatherManager manager;

    @BeforeEach
    public void init() {
        this.controller = new WeatherControllerImpl(this.manager);
    }

    @Test
    public void givenNoCity_whenGetWeather_shouldReturn400() {
        ResponseEntity<?> response = this.controller.getWeather("");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenNonExistingCity_whenGetWeather_shouldReturn404() {
        ResponseEntity<?> response = this.controller.getWeather("abc");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void givenExistingCity_whenGetWeather_shouldReturnStatus200() throws InvalidApiKeyException {
        when(this.manager.getCurrentWeather(any()))
                .thenReturn(Optional.of(new Weather()));

        ResponseEntity<?> response = this.controller.getWeather("Florianopolis");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Weather);
    }

    @Test
    public void givenInvalidApiKey_whenGetWeather_shouldReturnStatus500() throws InvalidApiKeyException {
        when(this.manager.getCurrentWeather(any()))
                .thenThrow(new InvalidApiKeyException());

        ResponseEntity<?> city = this.controller.getWeather("Florianopolis");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, city.getStatusCode());
    }
}
