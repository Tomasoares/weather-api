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

    private ValidateConfigurationCLR clr;

    public StartCommandLineRunners(ValidateConfigurationCLR clr) {
        super();
        this.clr = clr;
    }

    @Override
    public void run(String... args) {
        this.clr.runValidator();
    }


}
