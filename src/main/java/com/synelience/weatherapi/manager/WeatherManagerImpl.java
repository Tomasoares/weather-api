package com.synelience.weatherapi.manager;

import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.manager.api.WeatherManager;
import com.synelience.weatherapi.manager.converter.WeatherConverter;
import com.synelience.weatherapi.model.Weather;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import com.synelience.weatherapi.openweather.service.api.OpenWeatherService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WeatherManagerImpl implements WeatherManager {

    private OpenWeatherService service;

    public WeatherManagerImpl(OpenWeatherService service) {
        super();
        this.service = service;
    }

    public Optional<Weather> getCurrenWeather(String city) throws InvalidApiKeyException {
        Optional<CurrentWeather> currentWeather = this.service.getCurrentWeather(city);
        return currentWeather.map(WeatherConverter::fromCurrentWeather);
    }

}
