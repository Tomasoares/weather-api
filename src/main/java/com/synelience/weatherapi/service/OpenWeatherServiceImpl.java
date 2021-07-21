package com.synelience.weatherapi.service;

import com.synelience.weatherapi.model.Weather;
import com.synelience.weatherapi.model.external.CurrentWeather;
import com.synelience.weatherapi.service.api.OpenWeatherService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private RestTemplate template;

    public OpenWeatherServiceImpl(RestTemplate template) {
        super();
        this.template = template;
    }

    @Override
    public CurrentWeather getCurrentWeather(String city) {
        return null;
    }
}
