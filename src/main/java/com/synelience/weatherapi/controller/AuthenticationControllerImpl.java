package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.controller.api.AuthenticationController;
import com.synelience.weatherapi.exception.InvalidApiKeyException;
import com.synelience.weatherapi.manager.api.AuthenticationManager;
import com.synelience.weatherapi.manager.api.WeatherManager;
import com.synelience.weatherapi.model.Weather;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ResourceConstants.AUTHENTICATION)
public class AuthenticationControllerImpl implements AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherControllerImpl.class);

    private AuthenticationManager manager;

    public AuthenticationControllerImpl(AuthenticationManager manager) {
        super();
        this.manager = manager;
    }

    @GetMapping
    public ResponseEntity<String> getApiKey(@RequestHeader("Authorization") String credentials) {
        LOGGER.info("Received credentials>: {}", credentials);

        if (Strings.isBlank(credentials)) {
            LOGGER.debug("No credentials included");
            return ResponseEntity.badRequest().body("Blank credentials!");
        }

        if (!credentials.toLowerCase().startsWith("basic")) {
            LOGGER.debug("Credentials not in Basic format");
            return ResponseEntity.badRequest().body("Credentials not in Basic format!");
        }

        Optional<String> apiToken = this.manager.authenticate(credentials);
        LOGGER.info("Retrieved token: {}", apiToken);

        return apiToken.map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


}
