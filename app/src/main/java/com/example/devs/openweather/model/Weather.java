package com.example.devs.openweather.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Weather implements Serializable {

    @Expose
    private long id;

    @Expose
    private String main;

    @Expose
    private String description;

    @Expose
    private String icon;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
