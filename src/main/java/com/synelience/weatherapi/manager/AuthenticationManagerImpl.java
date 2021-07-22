package com.synelience.weatherapi.manager;

import com.synelience.weatherapi.manager.api.AuthenticationManager;
import com.synelience.weatherapi.security.ApiKeys;
import com.synelience.weatherapi.security.UsersConfiguration;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManagerImpl.class);

    private UsersConfiguration cfg;
    private ApiKeys keys;

    public AuthenticationManagerImpl(UsersConfiguration cfg, ApiKeys keys) {
        super();
        this.cfg = cfg;
        this.keys = keys;
    }

    @Override
    public Optional<String> authenticate(String credentials) {
        String[] decodedCredentials = this.decodeBasicAuthenticationString(credentials);
        String user = decodedCredentials[0];
        String password = decodedCredentials[1];

        LOGGER.debug("Received login request for user: {}", user);
        String targetPassword = cfg.getUsers().get(user);

        if (Strings.isBlank(targetPassword)) {
            LOGGER.warn("User {} not found!", user);
            return Optional.empty();
        }

        if(!password.equals(targetPassword)) {
            LOGGER.warn("Invalid password!");
            return Optional.empty();
        }

        return retrieveApiKey(user);
    }

    private Optional<String> retrieveApiKey(String user) {
        String apiKey = this.keys.getKeys().get(user);

        if (Strings.isBlank(apiKey)) {
            LOGGER.debug("Generating new ApiKey");
            apiKey = this.generateApiKey();
            this.keys.getKeys().put(user, apiKey);
        }

        return Optional.of(apiKey);
    }

    protected String[] decodeBasicAuthenticationString(String credentials) {
        String base64Credentials = credentials.substring("Basic".length()).trim();
        byte[] credBytes = Base64.getDecoder().decode(base64Credentials);

        String decodedCredentials = new String(credBytes, StandardCharsets.UTF_8);

        return decodedCredentials.split(":", 2);
    }

    private String generateApiKey() {
        return RandomStringUtils.randomAlphabetic(30);
    }
}
