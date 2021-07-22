package com.synelience.weatherapi.openweather.service;

import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherServiceTest {

    private OpenWeatherServiceImpl service;

    @Mock
    private WeatherEndpoint endpoint;

    @Mock
    private OpenWeatherConfiguration cfg;

    public OpenWeatherServiceTest() {
        super();
    }

    @BeforeEach
    public void init() {
        this.service = new OpenWeatherServiceImpl(endpoint, cfg);
    }

    @Test
    public void givenSuccesfulRequest_whenGetCurrentWeather_shouldReturnCurrentWeather() throws InvalidApiKeyException {
        when(endpoint.callCurrentWeatherEndpoint(any(), any(), any()))
                .thenReturn(ResponseEntity.ok(new CurrentWeather()));

        Optional<CurrentWeather> city = this.service.getCurrentWeather("city");

        assertTrue(!city.isEmpty());
    }

    @Test
    public void givenNonSuccesfulRequest_whenGetCurrentWeather_shouldReturnEmptyOptional() throws InvalidApiKeyException {
        when(endpoint.callCurrentWeatherEndpoint(any(), any(), any()))
                .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CurrentWeather()));

        Optional<CurrentWeather> city = this.service.getCurrentWeather("city");

        assertTrue(city.isEmpty());
    }

    @Test
    public void givenException_whenGetCurrentWeather_shouldReturnEmptyOptional() throws InvalidApiKeyException {
        when(endpoint.callCurrentWeatherEndpoint(any(), any(), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        Optional<CurrentWeather> city = this.service.getCurrentWeather("city");

        assertTrue(city.isEmpty());
    }

    @Test
    public void givenUnauthorized_whenGetCurrentWeather_shouldThrowInvalidApiKeyException() {
        when(endpoint.callCurrentWeatherEndpoint(any(), any(), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        assertThrows(InvalidApiKeyException.class, () -> this.service.getCurrentWeather("city"));
    }

}
