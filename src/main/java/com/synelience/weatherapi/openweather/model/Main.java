package com.synelience.weatherapi.openweather.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Main implements Serializable {

    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Integer pressure;
    private Integer humidity;

}
