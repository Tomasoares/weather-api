package com.synelience.weatherapi.openweather.endpoint;

import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherEndpointImpl implements WeatherEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherEndpointImpl.class);

    private OpenWeatherConfiguration cfg;
    private RestTemplate template;

    public WeatherEndpointImpl(OpenWeatherConfiguration cfg, RestTemplate template) {
        super();
        this.cfg = cfg;
        this.template = template;
    }

    @Override
    public ResponseEntity<CurrentWeather> callCurrentWeatherEndpoint(String city, String apiKey, String units) {
        URI uri = this.buildUrl(city, apiKey, units);
        LOGGER.debug("Making a HTTP call to the URL: {}", uri);

        return this.template.getForEntity(uri, CurrentWeather.class);
    }

    protected URI buildUrl(String city, String apiKey, String units) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.cfg.getUrl() + this.cfg.getWeatherUri());

        if (Strings.isNotBlank(city)) {
            builder.queryParam("q", city);
        }

        if (Strings.isNotBlank(apiKey)) {
            builder.queryParam("appid", apiKey);
        }

        if (Strings.isNotBlank(this.cfg.getUnits())) {
            builder.queryParam("units", units);
        }

        return builder.build().toUri();
    }
}
