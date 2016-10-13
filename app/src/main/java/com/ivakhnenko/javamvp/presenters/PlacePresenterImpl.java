package com.ivakhnenko.javamvp.presenters;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.location.places.Place;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.models.PlaceModel;
import com.ivakhnenko.javamvp.views.PlaceView;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 27.09.16.
 */

public class PlacePresenterImpl implements PlacePresenter {

    private PlaceModel placeModel;

    private PlaceView placeView;

    public PlacePresenterImpl(PlaceModel placeModel, PlaceView placeView) {
        this.placeModel = placeModel;
        this.placeView = placeView;
    }

    @Override
    public void getPlace() {
        placeView.showWaitingBar();
        placeModel.getCurrentPlace(new FeatureResultCallback<Place>() {
            @Override
            public void onResult(@Nullable Place result) {
                openPlace(result);
            }
        });
    }

    @Override
    public void getPlaceById(String id) {
        placeView.showWaitingBar();
        placeModel.getPlaceById(id, new FeatureResultCallback<Place>() {
            @Override
            public void onResult(@Nullable Place result) {
                openPlace(result);
            }
        });

    }

    @Override
    public void getPlacePhotos(@NonNull String placeId) {
        placeModel.getPlacePhotos(placeId, new FeatureResultCallback<List<Bitmap>>() {
            @Override
            public void onResult(@Nullable List<Bitmap> result) {
                placeView.setPlacePhotos(result);
            }
        });
    }

    @Override
    public void openPlace(Place place) {
        placeView.hideWaitingBar();
        placeView.setPlace(place);
    }
}
