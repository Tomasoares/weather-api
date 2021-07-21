package com.synelience.weatherapi.openweather.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Main implements Serializable {

    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;

}
