package com.ivakhnenko.javamvp.models;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ruslan on 02.11.16.
 */

public interface RotationModel {

    void onStart();

    void onStop();

    @Nullable
    Location getCurrentLocation();

    float calculateDistanceToObject(@NonNull Location object);

    float calculateBearingToObject(@NonNull Location object);


}
