package com.synelience.weatherapi.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties
@Data
public class UsersConfiguration {

    private Map<String, String> users;

}
