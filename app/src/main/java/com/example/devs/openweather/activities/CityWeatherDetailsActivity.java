package com.example.devs.openweather.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devs.openweather.R;
import com.example.devs.openweather.activities.base.BaseActivity;
import com.example.devs.openweather.database.DatabaseHelper;
import com.example.devs.openweather.model.City;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_city_weather_details)
public class CityWeatherDetailsActivity extends BaseActivity {

    @Extra
    City city;

    @Extra
    boolean showButtonSave;

    @ViewById(R.id.text_view_weather)
    protected TextView textViewLabelWeather;

    @ViewById(R.id.text_view_weather_description)
    protected TextView textViewLabelWeatherDescription;

    @ViewById(R.id.button_save_city)
    protected Button buttonSaveCity;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<City, Long> moviesDao;

    @AfterViews
    protected void afterViews() {
        final String weather = city.getCityWeatherList().get(0).getWeatherList().get(0).getMain();
        final String weatherDescription = city.getCityWeatherList().get(0).getWeatherList().get(0).getDescription();

        textViewLabelWeather.setText(weather);
        textViewLabelWeatherDescription.setText(weatherDescription);

        if (!showButtonSave) {
            buttonSaveCity.setVisibility(Button.GONE);
        }
    }

    public void buttonSaveOnClick(View view) {
        String msg = getString(R.string.successful_save);

        try {
            final List<City> cities = moviesDao.queryForEq(City.NAME, city.getName());

            if(!cities.isEmpty()) {
                final City localCity = cities.get(0);
                city.setId(localCity.getId());
                msg = getString(R.string.city_already_exist);
            }

            moviesDao.createOrUpdate(city);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            CitiesWeatherOverviewActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
