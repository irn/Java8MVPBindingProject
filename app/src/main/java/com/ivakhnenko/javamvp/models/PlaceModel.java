package com.ivakhnenko.javamvp.models;

import android.graphics.Bitmap;

import com.google.android.gms.location.places.Place;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlaceModel {

    void onStart();

    void onStop();

    void getCurrentPlace(FeatureResultCallback<Place> result);

    void getPlaceById(String id, FeatureResultCallback<Place> placeResult);

    void getPlacePhotos(String id, FeatureResultCallback<List<Bitmap>> photoResult);
}
