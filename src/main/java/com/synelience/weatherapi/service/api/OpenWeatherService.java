package com.synelience.weatherapi.service.api;

import com.synelience.weatherapi.model.Weather;
import com.synelience.weatherapi.model.external.CurrentWeather;

public interface OpenWeatherService {

    CurrentWeather getCurrentWeather(String city);
}
