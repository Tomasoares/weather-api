package com.synelience.weatherapi.manager.converter;

import com.synelience.weatherapi.model.Temperature;
import com.synelience.weatherapi.model.Weather;
import com.synelience.weatherapi.model.Wind;
import com.synelience.weatherapi.openweather.model.CurrentWeather;

import java.util.Optional;

public class WeatherConverter {

    public static Weather fromCurrentWeather(CurrentWeather old) {
        Wind wind = buildWind(old);
        Temperature temperature = buildTemperature(old);
        String condition = retrieveCondition(old);

        Integer humidity = null;
        Integer pressure = null;

        if (old.getMain() != null) {
            humidity = old.getMain().getHumidity();
            pressure = old.getMain().getPressure();
        }

        return Weather.builder().city(old.getName())
                .condition(condition)
                .humidity(humidity)
                .pressure(pressure)
                .temperature(temperature)
                .visibility(old.getVisibility())
                .wind(wind)
                .timestamp(old.getDt())
                .build();

    }

    protected static Wind buildWind(CurrentWeather old) {
        Wind.WindBuilder builder = Wind.builder();

        Optional.ofNullable(old.getWind())
                .ifPresent(w -> builder.degree(w.getDeg()).speed(w.getSpeed()));

        return builder.build();
    }

    protected static Temperature buildTemperature(CurrentWeather old) {
        Temperature.TemperatureBuilder builder = Temperature.builder();

         Optional.ofNullable(old.getMain())
                .ifPresent(m -> builder.current(m.getTemp()).feelsLike(m.getFeels_like())
                        .maximum(m.getTemp_max()).minimum(m.getTemp_min()));

         return builder.build();
    }

    protected static String retrieveCondition(CurrentWeather old) {
        com.synelience.weatherapi.openweather.model.Weather[] weathers = old.getWeathers();

        if (weathers == null || weathers.length == 0) {
            return null;
        }

        return weathers[0].getMain();
    }
}
