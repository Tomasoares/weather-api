package com.synelience.weatherapi.clr;

import com.synelience.weatherapi.manager.api.ValidateConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class ValidateConfigurationCLR {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateConfigurationCLR.class);

    private ValidateConfigurationManager manager;

    public ValidateConfigurationCLR(ValidateConfigurationManager manager) {
        super();
        this.manager = manager;
    }

    public boolean runValidator() {
        LOGGER.info("Checking Open Weather configured parameters...");

        try {
            this.manager.validateConfiguration();
            LOGGER.info("Parameters are ok!");
            return true;

        } catch (Exception e) {
            LOGGER.error("Error trying to send test request with configured parameters", e);
            return false;
        }
    }
}
