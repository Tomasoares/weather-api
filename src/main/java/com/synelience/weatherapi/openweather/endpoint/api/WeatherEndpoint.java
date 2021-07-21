package com.synelience.weatherapi.openweather.endpoint.api;

import com.synelience.weatherapi.openweather.model.CurrentWeather;
import org.springframework.http.ResponseEntity;

public interface WeatherEndpoint {

    ResponseEntity<CurrentWeather> callCurrentWeatherEndpoint(String city, String apiKey, String units);

}
