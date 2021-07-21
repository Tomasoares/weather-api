package com.synelience.weatherapi.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "open-weather")
@Getter
@Setter
public class OpenWeatherConfiguration {

    private String apiKey;
    private String url;
    private String weatherUri;
    private String units;

}
