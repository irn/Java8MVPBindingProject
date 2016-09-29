package com.ivakhnenko.javamvp.presenters;

import com.google.android.gms.location.places.Place;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlacePresenter {

    void getPlace(FeatureResultCallback<Place> placeResultCallback);

}
