package com.synelience.weatherapi.openweather;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@Disabled
public class OpenWeatherConfigurationTest {

    @Autowired
    private OpenWeatherConfiguration config;

    @Test
    public void givenApplicationBoot_itShouldApplyConfiguration() {
        assertEquals("value", config.getApiKey());
        assertEquals("api.openweathermap.org/data/2.5", config.getUrl());
        assertEquals("/weather", config.getWeatherUri());
        assertEquals("metric", config.getUnits());
    }

}
