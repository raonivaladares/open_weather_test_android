package com.example.devs.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CityWithWeather implements Serializable{

    @Expose
    private City city;

    @SerializedName("list")
    @Expose
    private List<CityWeather> cityWeatherList;

    public City getCity() {
        return city;
    }

    public List<CityWeather> getCityWeatherList() {
        return cityWeatherList;
    }
}

