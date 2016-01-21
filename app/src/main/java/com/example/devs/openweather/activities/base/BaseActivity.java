package com.example.devs.openweather.activities.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.devs.openweather.R;
import com.example.devs.openweather.util.Constants;
import com.example.devs.openweather.util.Util;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity
public class BaseActivity extends AppCompatActivity {

    @Bean
    protected Util util;

    protected boolean checkNetworkStatus(View view) {
        boolean result = true;

        if (util != null  && !util.isNetworkAvailable(this)) {
            Snackbar.make(view, getString(R.string.verify_connection), Snackbar.LENGTH_LONG).setAction(Constants.ACTION, null).show();
            result = false;
        }
        return result;
    }

    protected void showSnackBarMsg(final View view, final String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(Constants.ACTION, null).show();
    }
}

