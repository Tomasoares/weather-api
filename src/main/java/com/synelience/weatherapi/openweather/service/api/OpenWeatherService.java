package com.synelience.weatherapi.openweather.service.api;

import com.synelience.weatherapi.model.external.CurrentWeather;

public interface OpenWeatherService {

    CurrentWeather getCurrentWeather(String city);
}
