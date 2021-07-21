package com.synelience.weatherapi.controller;


import com.synelience.weatherapi.controller.api.WeatherController;
import com.synelience.weatherapi.model.Weather;
import org.springframework.http.ResponseEntity;

public class WeatherControllerImpl implements WeatherController {

    public ResponseEntity<Weather> getWeather(String city) {
        return null;
    }

}
