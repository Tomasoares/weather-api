package com.synelience.weatherapi.openweather.service;

import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.service.api.OpenWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;
import java.util.Optional;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);

    private final WeatherEndpoint endpoint;
    private final OpenWeatherConfiguration cfg;

    public OpenWeatherServiceImpl(WeatherEndpoint endpoint, OpenWeatherConfiguration configuration) {
        super();
        this.endpoint = endpoint;
        this.cfg = configuration;
    }

    @Override
    public Optional<CurrentWeather> getCurrentWeather(String city) throws InvalidApiKeyException {
        try {
            LOGGER.debug("Getting Current Weather from city {}", city);
            ResponseEntity<CurrentWeather> response = this.endpoint.callCurrentWeatherEndpoint(city, this.cfg.getApiKey(), this.cfg.getUnits());

            if (Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
                LOGGER.debug("Successful request: {}", response.getBody());
                return Optional.of(response.getBody());

            } else {
                LOGGER.warn("The request didn't return OK status code, instead it got {}", response.getStatusCode());
                LOGGER.warn("Message Body: {}", response.getStatusCode());

                return Optional.empty();
            }
        } catch (HttpClientErrorException e) {
            LOGGER.error("An error happened trying to access the API", e);

            if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
                LOGGER.info("Invalid APIKey: {}", this.cfg.getApiKey());
                throw new InvalidApiKeyException();
            } else {
                return Optional.empty();
            }
        }
    }
}
