package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.controller.api.HealthController;
import com.synelience.weatherapi.model.Heartbeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.HEALTH)
public class HealthControllerImpl implements HealthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthControllerImpl.class);

    @GetMapping
    @Override
    public ResponseEntity<Heartbeat> heartbeat() {
        LOGGER.debug("Retrieving heartbeat call");
        return ResponseEntity.ok(new Heartbeat("I'm Alive!"));
    }
}
