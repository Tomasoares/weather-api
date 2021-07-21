package com.synelience.weatherapi.openweather.endpoint.api;

import org.springframework.http.ResponseEntity;

public interface WeatherEndpoint {

    ResponseEntity<String> callCurrentWeatherEndpoint(String city);

}
