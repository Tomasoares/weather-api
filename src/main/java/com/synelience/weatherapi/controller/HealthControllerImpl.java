package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.controller.api.HealthController;
import com.synelience.weatherapi.model.Heartbeat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.HEALTH)
public class HealthControllerImpl implements HealthController {

    @GetMapping
    @Override
    public ResponseEntity<Heartbeat> heartbeat() {
        return ResponseEntity.ok(new Heartbeat("I'm Alive!"));
    }
}
