package com.synelience.weatherapi.manager.api;


import java.util.Optional;

public interface AuthenticationManager {

    Optional<String> authenticate(String credentials);

}
