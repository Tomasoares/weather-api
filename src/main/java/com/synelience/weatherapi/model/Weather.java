package com.synelience.weatherapi.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Weather implements Serializable {

    private String city;
    private long timestamp;
    private Temperature temperature;
    private Wind wind;
    private long visibility;
    private int pressure;
    private int humidity;
    private String condition;

}
