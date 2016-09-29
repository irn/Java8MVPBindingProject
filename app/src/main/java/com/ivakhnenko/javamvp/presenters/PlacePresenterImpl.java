package com.ivakhnenko.javamvp.presenters;

import com.google.android.gms.location.places.Place;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.models.PlaceModel;

/**
 * Created by Ruslan Ivakhnenko on 27.09.16.
 */

public class PlacePresenterImpl implements PlacePresenter {

    private PlaceModel placeModel;

    public PlacePresenterImpl(PlaceModel placeModel) {
        this.placeModel = placeModel;
    }

    @Override
    public void getPlace(FeatureResultCallback<Place> placeResultCallback) {
        placeModel.getCurrentPlace(placeResultCallback);
    }
}
