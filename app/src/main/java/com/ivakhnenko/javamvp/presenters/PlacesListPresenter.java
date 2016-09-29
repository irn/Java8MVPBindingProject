package com.ivakhnenko.javamvp.presenters;

import com.google.android.gms.location.places.Place;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 29.09.16.
 */

public interface PlacesListPresenter {

    void getPlaces(FeatureResultCallback<List<Place>> places);
}
