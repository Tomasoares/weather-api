package com.synelience.weatherapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {

    private String city;
    private Long timestamp;
    private Temperature temperature;
    private Wind wind;
    private Long visibility;
    private Integer pressure;
    private Integer humidity;
    private String condition;

}
