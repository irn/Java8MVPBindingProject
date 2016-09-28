package newfeatures.ivakhnenko.com.javamvp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import newfeatures.ivakhnenko.com.javamvp.R;
import newfeatures.ivakhnenko.com.javamvp.databinding.ActivityPlaceBinding;
import newfeatures.ivakhnenko.com.javamvp.interfaces.FeatureResultCallback;
import newfeatures.ivakhnenko.com.javamvp.models.PlaceModel;
import newfeatures.ivakhnenko.com.javamvp.models.PlaceModelImpl;
import newfeatures.ivakhnenko.com.javamvp.presenters.PlacePresenter;
import newfeatures.ivakhnenko.com.javamvp.presenters.PlacePresenterImpl;

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback {

    PlaceModel placeModel;

    PlacePresenter presenter;

    ActivityPlaceBinding binding;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_place);
        placeModel = new PlaceModelImpl(this);
        presenter = new PlacePresenterImpl(placeModel);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter.getPlace(new FeatureResultCallback<Place>() {
            @Override
            public void onResult(@Nullable Place result) {
                binding.setPlace(result);
                if (mMap != null) {
                    mMap.addMarker(new MarkerOptions().position(result.getLatLng()).title((String) result.getName()));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(result.getLatLng()));
                }
            }
        });
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
