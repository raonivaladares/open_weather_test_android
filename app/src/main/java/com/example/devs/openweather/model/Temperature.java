package com.example.devs.openweather.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Temperature implements Serializable {

    @Expose
    private float day;

    @Expose
    private float min;

    @Expose
    private float max;

    @Expose
    private float night;

    @Expose
    private float eve;

    @Expose
    private float morn;

}
