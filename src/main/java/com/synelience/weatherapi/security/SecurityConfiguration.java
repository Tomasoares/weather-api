package com.synelience.weatherapi.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected static final String API_KEY_HEADER_NAME = "Authorization";

    private ApiKeys apiKeys;

    public SecurityConfiguration(ApiKeys apiKeys) {
        super();
        this.apiKeys = apiKeys;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyFilter filter = new ApiKeyFilter();
        filter.setAuthenticationManager(new ApiKeyManager(apiKeys));

        http.addFilter(filter)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/weathers")
            .authenticated();
    }
}
