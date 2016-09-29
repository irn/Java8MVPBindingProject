package com.ivakhnenko.javamvp.presenters;

import com.google.android.gms.location.places.Place;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.models.PlacesListModel;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 27.09.16.
 */

public class PlacesListPresenterImpl implements PlacesListPresenter {

    private PlacesListModel placeModel;

    public PlacesListPresenterImpl(PlacesListModel placeModel) {
        this.placeModel = placeModel;
    }


    @Override
    public void getPlaces(FeatureResultCallback<List<Place>> places) {
        placeModel.getPlaces(places);
    }
}
