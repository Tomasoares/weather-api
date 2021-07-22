package com.synelience.weatherapi.manager.api;

import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.model.Weather;

import java.util.Optional;

public interface WeatherManager {

    Optional<Weather> getCurrentWeather(String city) throws InvalidApiKeyException;

}
