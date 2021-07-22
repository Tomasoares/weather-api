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
public class Temperature implements Serializable {

    private Double current;
    private Double feelsLike;
    private Double minimum;
    private Double maximum;

}
