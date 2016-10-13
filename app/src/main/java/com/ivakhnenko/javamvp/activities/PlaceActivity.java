package com.ivakhnenko.javamvp.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ivakhnenko.javamvp.R;
import com.ivakhnenko.javamvp.adapters.PhotoAdapter;
import com.ivakhnenko.javamvp.databinding.ActivityPlaceBinding;
import com.ivakhnenko.javamvp.models.PlaceModel;
import com.ivakhnenko.javamvp.models.PlaceModelImpl;
import com.ivakhnenko.javamvp.presenters.PlacePresenter;
import com.ivakhnenko.javamvp.presenters.PlacePresenterImpl;
import com.ivakhnenko.javamvp.views.PlaceView;

import java.util.List;

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback, PlaceView {

    PlaceModel placeModel;

    PlacePresenter presenter;

    ActivityPlaceBinding binding;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_place);
        placeModel = new PlaceModelImpl(this);
        presenter = new PlacePresenterImpl(placeModel, this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (getIntent() != null && getIntent().hasExtra("placeId")){
            String placeId = getIntent().getStringExtra("placeId");
            presenter.getPlaceById(placeId);
        } else {
            presenter.getPlace();
        }
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

    @Override
    public void setPlace(Place place) {
        binding.setPlace(place);
        presenter.getPlacePhotos(place.getId());
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title((String) place.getName()));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
        }
    }

    @Override
    public void setPlacePhotos(@NonNull final List<Bitmap> bitmaps) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recycleviewPhotos = (RecyclerView) findViewById(R.id.recycleViewPhotos);
                PhotoAdapter photoAdapter = new PhotoAdapter(bitmaps);
                recycleviewPhotos.setAdapter(photoAdapter);
            }
        });

    }

    @Override
    public void showWaitingBar() {

    }

    @Override
    public void hideWaitingBar() {

    }
}
