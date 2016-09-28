package newfeatures.ivakhnenko.com.java8mvpbindingproject.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.places.Place;

import newfeatures.ivakhnenko.com.java8mvpbindingproject.R;
import newfeatures.ivakhnenko.com.java8mvpbindingproject.interfaces.FeatureResultCallback;
import newfeatures.ivakhnenko.com.java8mvpbindingproject.models.PlaceModel;
import newfeatures.ivakhnenko.com.java8mvpbindingproject.models.PlaceModelImpl;
import newfeatures.ivakhnenko.com.java8mvpbindingproject.presenters.PlacePresenter;
import newfeatures.ivakhnenko.com.java8mvpbindingproject.presenters.PlacePresenterImpl;

public class MainActivity extends AppCompatActivity {

    PlaceModel placeModel;

    PlacePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeModel = new PlaceModelImpl(this);
        presenter = new PlacePresenterImpl(placeModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        placeModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        placeModel.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getPlace(new FeatureResultCallback<Place>() {
            @Override
            public void onResult(@Nullable Place result) {

            }
        });
    }
}
