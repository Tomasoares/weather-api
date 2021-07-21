package com.synelience.weatherapi.openweather.service.api;

import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.openweather.model.CurrentWeather;

import java.util.Optional;

public interface OpenWeatherService {

    Optional<CurrentWeather> getCurrentWeather(String city) throws InvalidApiKeyException;

}
