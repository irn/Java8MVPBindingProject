package com.ivakhnenko.javamvp.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ruslan on 05.11.16.
 */

public abstract class BaseLocationActivity extends AppCompatActivity {

    public static final int LOCATION_REQUEST_CODE = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
