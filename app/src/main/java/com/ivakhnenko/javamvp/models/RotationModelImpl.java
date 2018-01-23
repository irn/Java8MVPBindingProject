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
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.ivakhnenko.javamvp.activities.BaseLocationActivity;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.presenters.RotationPresenter;

/**
 * Created by ruslan on 02.11.16.
 */

public class RotationModelImpl extends BaseGpsModel implements RotationModel, LocationListener, SensorEventListener {

    private Activity mActivity;

    LocationManager locationManager;

    SensorManager sensorManager;

    private float[] mOrientation;

    private Location dstLocation;

    private FeatureResultCallback<Position> mCallback;
    boolean isStartSensor, isStartLocation;

    public RotationModelImpl(Activity mActivity) {

        this.mActivity = mActivity;

        locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);

        sensorManager = (SensorManager) mActivity.getSystemService(Context.SENSOR_SERVICE);

        mOrientation = new float[3];

        dstLocation = new Location(LocationManager.GPS_PROVIDER);
        dstLocation.setLatitude(52.73611749999998);
        dstLocation.setLongitude(6.091285156250009);
    }

    @Override
    public void onStart() {
        if (checkPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, BaseLocationActivity.LOCATION_REQUEST_CODE);
            return;
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return TODO;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 3, this);
        Sensor rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (rotationSensor != null) {
            sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
    public void setDirectionChanged(@Nullable FeatureResultCallback<Position> directionCallback) {
        mCallback = directionCallback;
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
        if (!isStartLocation){
            isStartLocation = true;
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivity, "Start location", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (isOrientationInitialized(mOrientation) && mCallback != null){
            float bearingTo = location.bearingTo(dstLocation);
            int distanceTo = Math.round(location.distanceTo(dstLocation));
//            Log.i(getClass().getSimpleName(), String.format("bearing = %f, distance = %d", bearingTo, distanceTo));
//            Log.i(getClass().getSimpleName(), String.format("az in d = %f, azimuth = %f", Math.toDegrees(mOrientation[0]), mOrientation[0]));
//            Log.i(getClass().getSimpleName(), String.format("azimuth = %f, pitch = %f, roll = %f", Math.toDegrees(mOrientation[0]), Math.toDegrees(mOrientation[1]), Math.toDegrees(mOrientation[2])));
            Position position = new Position();
            position.setDistance(distanceTo);
            position.setLocationBearing(bearingTo);
            position.setDeviceAzimuth(Math.toDegrees(mOrientation[0]));
            mCallback.onResult(position);
        }
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
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
        float[] values = SensorManager.getOrientation(rotationMatrix, mOrientation);
        if (!isStartSensor){
            isStartSensor = true;
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivity, "Start sensor", Toast.LENGTH_LONG).show();
                }
            });

        }
        Log.i(getClass().getSimpleName(), String.format("azimuth = %f, pitch = %f, roll = %f", Math.toDegrees(mOrientation[0]), Math.toDegrees(mOrientation[1]), Math.toDegrees(mOrientation[2])));
//        Log.i(getClass().getSimpleName(), String.format("azimuth = %f, pitch = %f, roll = %f", values[0], values[1], values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private boolean isOrientationInitialized(float[] mOrientation){
        return mOrientation != null && mOrientation.length == 3 && mOrientation[1] != 0 && mOrientation[2] != 0;
    }
}
