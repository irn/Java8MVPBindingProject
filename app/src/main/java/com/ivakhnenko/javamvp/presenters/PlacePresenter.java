package com.ivakhnenko.javamvp.presenters;

import com.google.android.gms.location.places.Place;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlacePresenter {

    void getPlace();

    void openPlace(Place place);

}
