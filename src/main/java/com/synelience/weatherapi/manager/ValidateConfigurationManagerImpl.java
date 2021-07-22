package com.synelience.weatherapi.manager;

import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.manager.api.ValidateConfigurationManager;
import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import com.synelience.weatherapi.openweather.service.api.OpenWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateConfigurationManagerImpl implements ValidateConfigurationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateConfigurationManagerImpl.class);

    private OpenWeatherConfiguration cfg;
    private OpenWeatherService service;

    public ValidateConfigurationManagerImpl(OpenWeatherConfiguration cfg, OpenWeatherService service) {
        super();
        this.cfg = cfg;
        this.service = service;
    }

    @Override
    public void validateConfiguration() throws Exception {
        String testCity = this.cfg.getTestCity();
        LOGGER.info("Using unit type: {}", this.cfg.getUnits());
        LOGGER.info("Using base url: {}", this.cfg.getUrl());
        LOGGER.info("Using target uri: {}", this.cfg.getWeatherUri());
        LOGGER.info("Using Api Key: {}", this.cfg.getApiKey());

        Optional<CurrentWeather> response = this.service.getCurrentWeather(testCity);

        response.ifPresent(r -> LOGGER.info("Successful request. Retrieved:  {}", r));
        response.orElseThrow(() -> new Exception("It wasn't possible retrieve Weather information"));
    }
}
