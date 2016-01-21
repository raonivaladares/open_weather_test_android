package com.example.devs.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

    public static final String NAME = "name";

    @DatabaseField
    @SerializedName("id")
    @Expose
    private long id;

    @DatabaseField
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("list")
    @Expose
    private List<CityWeather> cityWeatherList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<CityWeather> getCityWeatherList() {
        return cityWeatherList;
    }
}
