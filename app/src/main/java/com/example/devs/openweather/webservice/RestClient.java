package com.example.devs.openweather.webservice;

import com.example.devs.openweather.model.CityWithWeather;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;


@Rest(rootUrl = "http://api.openweathermap.org/data/2.5/forecast", converters = { GsonHttpMessageConverter.class })
public interface RestClient {

    @Get("/daily?q={city}&APPID=afe02b4969b9b87688ab8b24c4ee5a77")
    CityWithWeather searchCityWeather(final String city);

}