package com.synelience.weatherapi.openweather.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Weather implements Serializable {

    String main;
    String description;

}
