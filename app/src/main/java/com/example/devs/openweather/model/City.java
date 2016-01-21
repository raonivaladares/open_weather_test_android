package com.example.devs.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

    public static final String NAME = "name";

    @DatabaseField
    @Expose
    private long id;

    @DatabaseField
    @Expose
    private String name;

    @Expose
    private String country;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
