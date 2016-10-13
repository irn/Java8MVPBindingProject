package com.ivakhnenko.javamvp.presenters;

import android.support.annotation.NonNull;

import com.google.android.gms.location.places.Place;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlacePresenter {

    void getPlace();

    void getPlaceById(@NonNull String id);

    void getPlacePhotos(@NonNull String placeId);

    void openPlace(@NonNull Place place);

}
