package com.synelience.weatherapi.manager.api;

import com.synelience.weatherapi.exception.InvalidApiKeyException;

public interface ValidateConfigurationManager {

    public void validateConfiguration() throws InvalidApiKeyException, Exception;

}
