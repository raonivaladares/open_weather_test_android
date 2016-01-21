package com.example.devs.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CityWeather implements Serializable {

    @Expose
    @SerializedName("temp")
    private Temperature temperature;

    @Expose
    private float pressure;

    @Expose
    private float humidity;

    @SerializedName("weather")
    @Expose
    private List<Weather> weatherList;

    public List<Weather> getWeatherList() {
        return weatherList;
    }
}
