package com.ivakhnenko.javamvp.activities;

import android.os.Bundle;
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

import java.util.List;

public class PlacesListActivity extends AppCompatActivity {

    PlacesListModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView1);
        model = new PlacesListModelImpl(this);
        PlacesListPresenter presenter = new PlacesListPresenterImpl(model);
        presenter.getPlaces(new FeatureResultCallback<List<Place>>() {
            @Override
            public void onResult(@Nullable List<Place> result) {
                PlacesAdapter adapter = new PlacesAdapter(result);
                recyclerView.setAdapter(adapter);

            }
        });
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
}
