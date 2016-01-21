package com.example.devs.openweather.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.devs.openweather.R;
import com.example.devs.openweather.activities.base.BaseActivity;
import com.example.devs.openweather.model.City;
import com.example.devs.openweather.util.Constants;
import com.example.devs.openweather.webservice.RestClient;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@EActivity(R.layout.activity_search)
public class SearchActivity extends BaseActivity {

    ProgressDialog progress;

    @ViewById(R.id.editText)
    EditText editText;

    @RestService
    protected RestClient restClient;


    @Click(R.id.button_search_movie)
    public void onClickSearchMovie() {
        if (checkNetworkStatus(editText)) {
            final View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            try {
                getMovie();
            } catch (Exception e) {
                e.getMessage();
                showSnackBarMsg(editText, getString(R.string.generic_error));
            }
        }
    }

    @Background
    protected void getMovie() {
        try {
            String searchedCity = URLEncoder.encode(editText.getText().toString(), Constants.UTF8);
            if (!editText.getText().toString().isEmpty()) {
                showProgressDialog();
                City city = restClient.searchCityWeather(searchedCity);
                dismissProgressDialog();
                if(city == null) {
                    showSnackBarMsg(editText, "O.o");
                } else {
                    openDetails(city);
                }
            } else {
                showSnackBarMsg(editText, getString(R.string.field_not_filled));
            }

        } catch (UnsupportedEncodingException e) {
            showSnackBarMsg(editText, getString(R.string.search_not_found));
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            showSnackBarMsg(editText, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void showProgressDialog() {
        progress = ProgressDialog.show(this, getString(R.string.loading),
                getString(R.string.wait), true);
    }

    @UiThread
    public void dismissProgressDialog() {
        if(progress != null) {
            progress.dismiss();
        }
    }

    @UiThread
    public void openDetails(final City city) {
        CityWeatherDetailsActivity_.intent(this)
                .city(city)
                .showButtonSave(true)
                .start();
    }
}

