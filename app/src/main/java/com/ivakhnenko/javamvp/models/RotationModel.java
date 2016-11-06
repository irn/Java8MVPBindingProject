package com.ivakhnenko.javamvp.models;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

/**
 * Created by ruslan on 02.11.16.
 */

public interface RotationModel {

    void onStart();

    void onStop();

    @Nullable
    Location getCurrentLocation();

    void setDirectionChanged(@Nullable FeatureResultCallback<Position> directionCallback);

    float calculateDistanceToObject(@NonNull Location object);

    float calculateBearingToObject(@NonNull Location object);


}
