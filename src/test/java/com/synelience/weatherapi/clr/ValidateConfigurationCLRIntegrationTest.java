package com.synelience.weatherapi.clr;

import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class ValidateConfigurationCLRIntegrationTest {

    @Autowired
    private ValidateConfigurationCLR clr;

    @MockBean
    private WeatherEndpoint endpoint;

    @Test
    public void givenCorrectParameters_whenApplicationStart_shouldShowASuccessfulRequestOnLogging() {
        when(endpoint.callCurrentWeatherEndpoint(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new CurrentWeather()));

        assertTrue(this.clr.runValidator());
    }
}
