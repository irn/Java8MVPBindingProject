package com.ivakhnenko.javamvp.views;

import com.google.android.gms.location.places.Place;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlaceView {

    void setPlace(Place place);

    void showWaitingBar();

    void hideWaitingBar();
}
