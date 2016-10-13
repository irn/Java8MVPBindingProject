package com.ivakhnenko.javamvp.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.location.places.Place;
import com.ivakhnenko.javamvp.R;
import com.ivakhnenko.javamvp.adapters.PlacesAdapter;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.models.PlacesListModel;
import com.ivakhnenko.javamvp.models.PlacesListModelImpl;
import com.ivakhnenko.javamvp.presenters.PlacesListPresenter;
import com.ivakhnenko.javamvp.presenters.PlacesListPresenterImpl;
import com.ivakhnenko.javamvp.utils.Const;

import java.util.List;

public class PlacesListActivity extends AppCompatActivity {

    PlacesListModel model;

    PlacesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        model = new PlacesListModelImpl(this);
        presenter = new PlacesListPresenterImpl(model);
        getPlaces();

    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        model.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Const.ACCESS_LOCATION_REQUEST){
            for (int i = 0; i < permissions.length; i++) {
                if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[i]) && grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    getPlaces();
                }
            }
        }
    }

    private void getPlaces(){
        presenter.getPlaces(new FeatureResultCallback<List<Place>>() {
            @Override
            public void onResult(@Nullable List<Place> result) {
                PlacesAdapter adapter = new PlacesAdapter(result);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView1);
                recyclerView.setAdapter(adapter);

            }
        });
    }
}
