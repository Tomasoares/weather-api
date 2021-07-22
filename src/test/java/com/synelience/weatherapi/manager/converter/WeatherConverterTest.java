package com.synelience.weatherapi.manager.converter;

import com.synelience.weatherapi.model.Temperature;
import com.synelience.weatherapi.openweather.model.CurrentWeather;
import com.synelience.weatherapi.openweather.model.Main;
import com.synelience.weatherapi.openweather.model.Weather;
import com.synelience.weatherapi.openweather.model.Wind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WeatherConverterTest {

    @Test
    public void givenExternalWindModel_whenBuildWind_shouldReturnInternalInstance() {
        Wind wind = new Wind();
        wind.setDeg(35);
        wind.setSpeed(230.5d);

        CurrentWeather external = new CurrentWeather();
        external.setWind(wind);

        com.synelience.weatherapi.model.Wind internal = WeatherConverter.buildWind(external);

        assertEquals(wind.getDeg(), internal.getDegree());
        assertEquals(wind.getSpeed(), internal.getSpeed());
    }

    @Test
    public void givenNoWindModel_whenBuildWind_shouldReturnInternalInstance() {
        CurrentWeather external = new CurrentWeather();
        external.setWind(null);

        com.synelience.weatherapi.model.Wind internal = WeatherConverter.buildWind(external);

        assertNull(internal.getDegree());
        assertNull(internal.getSpeed());
    }

    @Test
    public void givenExternalMainModel_whenBuildTemperature_shouldReturnInternalInstance() {
        Main main = new Main();
        main.setTemp(35.0d);
        main.setTemp_max(230.5d);
        main.setTemp_min(38.0d);
        main.setFeels_like(230.5d);

        CurrentWeather external = new CurrentWeather();
        external.setMain(main);

        Temperature internal = WeatherConverter.buildTemperature(external);

        assertEquals(main.getTemp(), internal.getCurrent());
        assertEquals(main.getTemp_max(), internal.getMaximum());
        assertEquals(main.getTemp_min(), internal.getMinimum());
        assertEquals(main.getFeels_like(), internal.getFeelsLike());
    }

    @Test
    public void givenNoMainModel_whenBuildTemperature_shouldReturnInternalInstance() {
        CurrentWeather external = new CurrentWeather();
        external.setMain(null);

        Temperature internal = WeatherConverter.buildTemperature(external);

        assertNull(internal.getCurrent());
        assertNull(internal.getMaximum());
        assertNull(internal.getMinimum());
        assertNull(internal.getFeelsLike());
    }

    @Test
    public void givenExternalWeatherModel_whenFromCurrentWeather_shouldReturnInternalInstance() {
        Main main = new Main();
        main.setHumidity(1);
        main.setPressure(2);

        Weather weather = new Weather();
        weather.setMain("raining");

        CurrentWeather external = new CurrentWeather();
        external.setDt(System.currentTimeMillis());
        external.setWeather(new Weather[] {weather});
        external.setMain(main);
        external.setVisibility(123L);

        com.synelience.weatherapi.model.Weather internal = WeatherConverter.fromCurrentWeather(external);

        assertEquals(main.getHumidity(), internal.getHumidity());
        assertEquals(main.getPressure(), internal.getPressure());
        assertEquals(weather.getMain(), internal.getCondition());
        assertEquals(external.getDt(), internal.getTimestamp());
        assertEquals(external.getVisibility(), internal.getVisibility());
    }
}
