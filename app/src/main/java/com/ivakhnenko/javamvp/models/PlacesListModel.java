package com.ivakhnenko.javamvp.models;

import com.google.android.gms.location.places.Place;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlacesListModel {

    void onStart();

    void onStop();

    void getPlaces(FeatureResultCallback<List<Place>> places);
}
