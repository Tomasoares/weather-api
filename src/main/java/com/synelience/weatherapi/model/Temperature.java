package com.synelience.weatherapi.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Temperature implements Serializable {

    private double current;
    private double feels_like;
    private double minimum;
    private double maximum;

}
