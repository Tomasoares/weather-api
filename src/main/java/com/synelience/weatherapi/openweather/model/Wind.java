package com.synelience.weatherapi.openweather.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Wind implements Serializable {

   private double speed;
   private int deg;

}
