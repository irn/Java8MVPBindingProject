package com.ivakhnenko.javamvp.views;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.android.gms.location.places.Place;

import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlaceView {

    void setPlace(@NonNull Place place);

    void setPlacePhotos(@NonNull List<Bitmap> bitmaps);

    void showWaitingBar();

    void hideWaitingBar();
}
