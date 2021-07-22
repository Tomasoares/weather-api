package com.synelience.weatherapi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public class ApiKeyManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication auth) {
        String principal = (String) auth.getPrincipal();

        if(principal.equals("teste")) {
            auth.setAuthenticated(true);
            return auth;
        } else {
            throw new BadCredentialsException("Invalid API Key");
        }
    }
}
