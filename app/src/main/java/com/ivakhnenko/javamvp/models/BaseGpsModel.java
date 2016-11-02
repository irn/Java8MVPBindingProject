package com.ivakhnenko.javamvp.models;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

/**
 * Created by ruslan on 02.11.16.
 */

public class BaseGpsModel {

    private Location mCurrentLocation;

    protected boolean checkPermission(Activity activity, String permission){
        return ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED;
    }

    public Location getCurrentLocation() {
        return mCurrentLocation;
    }

    public void setCurrentLocation(Location mCurrentLocation) {
        this.mCurrentLocation = mCurrentLocation;
    }
}
