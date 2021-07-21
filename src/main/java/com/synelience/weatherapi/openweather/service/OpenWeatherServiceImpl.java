package com.synelience.weatherapi.openweather.service;

import com.synelience.weatherapi.openweather.model.CurrentWeather;
import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.service.api.OpenWeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final WeatherEndpoint endpoint;
    private final OpenWeatherConfiguration cfg;

    public OpenWeatherServiceImpl(WeatherEndpoint endpoint, OpenWeatherConfiguration configuration) {
        super();
        this.endpoint = endpoint;
        this.cfg = configuration;
    }

    @Override
    public CurrentWeather getCurrentWeather(String city) {
        ResponseEntity<String> response = this.endpoint.callCurrentWeatherEndpoint(city, this.cfg.getApiKey(), this.cfg.getUnits());

        if (Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
            System.out.println(response);
        } else {

        }

        return null;
    }
}
