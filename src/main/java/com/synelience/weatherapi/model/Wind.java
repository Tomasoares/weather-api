package com.synelience.weatherapi.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Wind implements Serializable {

    private double speed;
    private int degree;

}
