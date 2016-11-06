package com.ivakhnenko.javamvp.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.ivakhnenko.javamvp.R;
import com.ivakhnenko.javamvp.models.RotationModel;
import com.ivakhnenko.javamvp.models.RotationModelImpl;
import com.ivakhnenko.javamvp.presenters.RotationPresenter;

public class RotationActivity extends BaseLocationActivity {

    RotationModel rotationModel;

    RotationPresenter rotationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);
        rotationModel = new RotationModelImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rotationModel.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        rotationModel.onStop();
    }
}
