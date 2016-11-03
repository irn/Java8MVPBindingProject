package com.ivakhnenko.javamvp.models;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by ruslan on 02.11.16.
 */

public class RotationModelImpl extends BaseGpsModel implements RotationModel, LocationListener, SensorEventListener {

    private Activity mActivity;

    LocationManager locationManager;

    SensorManager sensorManager;

    public RotationModelImpl(Activity mActivity) {
        this.mActivity = mActivity;

        locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);

        sensorManager = (SensorManager) mActivity.getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onStart() {
        if (checkPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return TODO;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, this);
        Sensor rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (rotationSensor != null) {
            sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onStop() {
        locationManager.removeUpdates(this);
        sensorManager.unregisterListener(this);
    }

    @Override
    public Location getCurrentLocation() {
        return super.getCurrentLocation();
    }

    @Override
    public float calculateDistanceToObject(Location object) {
        Location currentLocation = getCurrentLocation();
        return currentLocation != null ? currentLocation.distanceTo(object) : 0f;
    }

    @Override
    public float calculateBearingToObject(@NonNull Location object) {
        Location currentLocation = getCurrentLocation();
        return currentLocation != null ? currentLocation.bearingTo(object) : 0f;
    }

    @Override
    public void onLocationChanged(Location location) {
        setCurrentLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
