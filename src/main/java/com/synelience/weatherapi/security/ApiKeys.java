package com.synelience.weatherapi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class ApiKeys {

    private Map<String, String> keys = new HashMap<>();

}
