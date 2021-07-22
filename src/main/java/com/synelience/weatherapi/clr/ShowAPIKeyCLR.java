package com.synelience.weatherapi.clr;

import com.synelience.weatherapi.security.ApiKey;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShowAPIKeyCLR {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAPIKeyCLR.class);

    private final ApiKey key;

    public ShowAPIKeyCLR(ApiKey key) {
        super();
        this.key = key;
    }

    public boolean runValidator() {
        LOGGER.info("Generated APIKey: {}", this.key.getKey());
        return true;
    }

}
