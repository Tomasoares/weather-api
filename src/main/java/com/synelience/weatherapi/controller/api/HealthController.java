package com.synelience.weatherapi.controller.api;

import com.synelience.weatherapi.model.Heartbeat;
import org.springframework.http.ResponseEntity;

public interface HealthController {

    ResponseEntity<Heartbeat> heartbeat();

}
