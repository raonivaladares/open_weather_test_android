package com.example.devs.openweather.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.devs.openweather.R;
import com.example.devs.openweather.activities.base.BaseActivity;
import com.example.devs.openweather.adapters.CitiesRecycleViewAdapter;
import com.example.devs.openweather.database.DatabaseHelper;
import com.example.devs.openweather.model.City;
import com.example.devs.openweather.util.DividerItemDecoration;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_cities_weather_overview)
public class CitiesWeatherOverviewActivity extends BaseActivity{
    private CitiesRecycleViewAdapter adapter;

    @ViewById(R.id.recycleView)
    protected RecyclerView recyclerView;

    @ViewById(R.id.textViewLabel)
    protected TextView textViewLabel;

    @OrmLiteDao(helper = DatabaseHelper.class)
    protected Dao<City, Long> moviesDao;

    @AfterViews
    protected void afterViews() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final List<City> cities = getDataSet();

        if(cities != null) {
            final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new CitiesRecycleViewAdapter(cities);
            recyclerView.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);
        } else {
            recyclerView.setVisibility(View.GONE);
            textViewLabel.setVisibility(View.VISIBLE);
        }

        adapter.setOnItemClickListener(new CitiesRecycleViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View view) {
//                final City city;
//                try {
//                    city = adapter.getItem(position);
//                    if (city != null) {
//                        CityWeatherDetailsActivity_.intent(view.getContext())
//                                .city(city)
//                                .showButtonSave(false)
//                                .start();
//                    }
//                } catch (Exception e) {
//                    e.getStackTrace();
//                    showSnackBarMsg(textViewLabel, getString(R.string.generic_error));
//                }
            }
        });
    }

    @Click(R.id.add_city)
    public void onClickAddMovie(View view) {
        SearchActivity_.intent(this).start();
    }

    private List<City> getDataSet() {
        List<City> cities = null;
        try {
            cities =  moviesDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            showSnackBarMsg(textViewLabel, getString(R.string.generic_error));
        }
        return cities;
    }
}
