package com.synelience.weatherapi.security;

import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@Disabled
public class UsersConfigurationTest {

    @Autowired
    private UsersConfiguration cfg;

    @Test
    public void givenApplicationBoot_itShouldApplyConfiguration() {
        String password = cfg.getUsers().get("synelience");

        assertEquals("synelience", password);
    }


}
