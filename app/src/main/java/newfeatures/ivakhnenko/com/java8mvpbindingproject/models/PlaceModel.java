package newfeatures.ivakhnenko.com.java8mvpbindingproject.models;

import com.google.android.gms.location.places.Place;

import newfeatures.ivakhnenko.com.java8mvpbindingproject.interfaces.FeatureResultCallback;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlaceModel {

    void onStart();

    void onStop();

    void getCurrentPlace(FeatureResultCallback<Place> result);
}
