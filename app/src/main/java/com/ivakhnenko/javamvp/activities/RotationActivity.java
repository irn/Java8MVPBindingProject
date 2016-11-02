package com.ivakhnenko.javamvp.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ivakhnenko.javamvp.R;

public class RotationActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] rotationMatrix = new float[9];
        sensorManager.getRotationMatrix(rotationMatrix, null, event.values, null);
        float[] values = sensorManager.getOrientation(rotationMatrix, event.values);
        Log.i(getClass().getSimpleName(), String.format("azimuth = %f, pitch = %f, roll = %f", values[0], values[1], values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor defaultRotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, defaultRotationSensor, SensorManager.SENSOR_DELAY_UI, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
