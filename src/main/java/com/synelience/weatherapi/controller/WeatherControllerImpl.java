package com.synelience.weatherapi.controller;


import com.synelience.weatherapi.controller.api.WeatherController;
import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.manager.api.WeatherManager;
import com.synelience.weatherapi.model.Weather;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(ResourceConstants.WEATHER)
public class WeatherControllerImpl implements WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherControllerImpl.class);

    private WeatherManager manager;

    public WeatherControllerImpl(WeatherManager manager) {
        super();
        this.manager = manager;
    }

    @GetMapping
    public ResponseEntity<? extends Object> getWeather(@RequestParam String city) {
        LOGGER.info("Received request with city param: {}", city);

        if (Strings.isBlank(city)) {
            LOGGER.debug("No city parameter included");
            return ResponseEntity.badRequest().body("Blank city parameter!");
        }

        try {
            Optional<Weather> currentWeather = this.manager.getCurrentWeather(city);
            LOGGER.info("Retrieved following weather object: {}", currentWeather);

            return currentWeather.map(r -> ResponseEntity.ok().body(r))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidApiKeyException e) {
            LOGGER.warn("Invalid APIKey error", e);
            return ResponseEntity.internalServerError().body("Provided APIKey to access Open Weather API is invalid!");
        }
    }

}
