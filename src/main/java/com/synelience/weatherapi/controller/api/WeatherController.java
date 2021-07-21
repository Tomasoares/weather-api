package com.synelience.weatherapi.controller.api;

import com.synelience.weatherapi.model.Heartbeat;
import com.synelience.weatherapi.model.Weather;
import org.springframework.http.ResponseEntity;

public interface WeatherController {

    public ResponseEntity<Weather> getWeather(String city);

}
