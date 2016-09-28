package newfeatures.ivakhnenko.com.java8mvpbindingproject.presenters;

import com.google.android.gms.location.places.Place;

import newfeatures.ivakhnenko.com.java8mvpbindingproject.interfaces.FeatureResultCallback;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlacePresenter {

    void getPlace(FeatureResultCallback<Place> placeResultCallback);

    void getPlacesList(FeatureResultCallback<Place> placeResultCallback);
}
