package com.synelience.weatherapi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public class ApiKeyManager implements AuthenticationManager {

    private static final String API_KEY_PREFIX = "Apikey";

    private ApiKey key;

    public ApiKeyManager(ApiKey key) {
        super();
        this.key = key;
    }

    @Override
    public Authentication authenticate(Authentication auth) {
        String principal = (String) auth.getPrincipal();

        if(principal.equals(key.getKey())) {
            auth.setAuthenticated(true);
            return auth;
        } else {
            throw new BadCredentialsException("Invalid API Key");
        }
    }
}
