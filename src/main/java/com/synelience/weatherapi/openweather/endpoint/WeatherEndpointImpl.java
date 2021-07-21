package com.synelience.weatherapi.openweather.endpoint;

import com.synelience.weatherapi.openweather.endpoint.api.WeatherEndpoint;
import com.synelience.weatherapi.openweather.OpenWeatherConfiguration;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherEndpointImpl implements WeatherEndpoint {

    private OpenWeatherConfiguration cfg;
    private RestTemplate template;

    public WeatherEndpointImpl(OpenWeatherConfiguration cfg, RestTemplate template) {
        super();
        this.cfg = cfg;
        this.template = template;
    }

    @Override
    public ResponseEntity<String> callCurrentWeatherEndpoint(String city) {
        URI uri = this.buildUrl(city);
        ResponseEntity<String> response = this.template.getForEntity(uri, String.class);
        return response;
    }

    protected URI buildUrl(String city) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.cfg.getUrl() + this.cfg.getWeatherUri());

        if (Strings.isNotBlank(city)) {
            builder.queryParam("q", city);
        }

        if (Strings.isNotBlank(this.cfg.getApiKey())) {
            builder.queryParam("appid", this.cfg.getApiKey());
        }

        if (Strings.isNotBlank(this.cfg.getUnits())) {
            builder.queryParam("units", this.cfg.getUnits());
        }

        return builder.build().toUri();
    }
}
