package com.synelience.weatherapi.openweather.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class CurrentWeather implements Serializable {

    private Weather weather;
    private Main main;
    private long visibility;
    private Wind wind;
    private String name;
    private long dt;

}
