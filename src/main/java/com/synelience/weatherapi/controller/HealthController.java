package com.synelience.weatherapi.controller;

import com.synelience.weatherapi.model.Heartbeat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstants.HEALTH)
public class HealthController {

    @GetMapping
    public ResponseEntity<?> heartbeat() {
        return ResponseEntity.ok(new Heartbeat("I'm Alive!"));
    }
}
