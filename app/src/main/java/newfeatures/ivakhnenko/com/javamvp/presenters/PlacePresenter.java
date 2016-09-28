package newfeatures.ivakhnenko.com.javamvp.presenters;

import com.google.android.gms.location.places.Place;

import newfeatures.ivakhnenko.com.javamvp.interfaces.FeatureResultCallback;

/**
 * Created by Ruslan Ivakhnenko on 22.09.16.
 */

public interface PlacePresenter {

    void getPlace(FeatureResultCallback<Place> placeResultCallback);

}
