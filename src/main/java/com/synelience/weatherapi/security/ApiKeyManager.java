package com.synelience.weatherapi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public class ApiKeyManager implements AuthenticationManager {

    private static final String API_KEY_PREFIX = "Apikey";

    private ApiKeys keys;

    public ApiKeyManager(ApiKeys keys) {
        super();
        this.keys = keys;
    }

    @Override
    public Authentication authenticate(Authentication auth) {
        String principal = (String) auth.getPrincipal();

        boolean authenticated = this.keys.getKeys().values().stream()
                .anyMatch(key -> key.equals(principal));

        if(authenticated) {
            auth.setAuthenticated(true);
            return auth;
        } else {
            throw new BadCredentialsException("Invalid API Key");
        }
    }
}
