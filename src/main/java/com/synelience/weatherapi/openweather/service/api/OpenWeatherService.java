package com.synelience.weatherapi.openweather.service.api;

import com.synelience.weatherapi.openweather.model.CurrentWeather;

public interface OpenWeatherService {

    CurrentWeather getCurrentWeather(String city);
}
