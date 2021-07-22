package com.synelience.weatherapi.clr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class StartCommandLineRunners implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartCommandLineRunners.class);

    private ValidateConfigurationCLR validateConfiguration;

    public StartCommandLineRunners(ValidateConfigurationCLR validateConfiguration) {
        super();
        this.validateConfiguration = validateConfiguration;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Running CommandLineRunners");
        this.validateConfiguration.runValidator();
    }


}
