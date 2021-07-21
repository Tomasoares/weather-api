package com.synelience.weatherapi.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpenWeatherConfigurationTest {

    @Autowired
    private OpenWeatherConfiguration config;

    @Test
    @Disabled
    public void givenApplicationBoot_itShouldApplyConfiguration() {
        assertEquals("value", config.getApiKey());
        assertEquals("api.openweathermap.org/data/2.5/", config.getUrl());
        assertEquals("/weather", config.getWeatherUri());
        assertEquals("metric", config.getUnits());
    }

}
