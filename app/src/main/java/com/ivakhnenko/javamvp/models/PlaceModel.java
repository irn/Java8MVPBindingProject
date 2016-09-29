package com.ivakhnenko.javamvp.models;

import com.google.android.gms.location.places.Place;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlaceModel {

    void onStart();

    void onStop();

    void getCurrentPlace(FeatureResultCallback<Place> result);
}
