package com.synelience.weatherapi.openweather.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class CurrentWeather implements Serializable {

    private Weather[] weathers;
    private Main main;
    private Long visibility;
    private Wind wind;
    private String name;
    private Long dt;

}
