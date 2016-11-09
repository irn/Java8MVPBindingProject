package com.ivakhnenko.javamvp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ivakhnenko.javamvp.R;
import com.ivakhnenko.javamvp.databinding.ActivityRotationBinding;
import com.ivakhnenko.javamvp.models.Position;
import com.ivakhnenko.javamvp.models.RotationModel;
import com.ivakhnenko.javamvp.models.RotationModelImpl;
import com.ivakhnenko.javamvp.presenters.RotationPresenter;
import com.ivakhnenko.javamvp.presenters.RotationPresenterImpl;
import com.ivakhnenko.javamvp.views.RotationView;

public class RotationActivity extends BaseLocationActivity implements RotationView{

    RotationModel rotationModel;

    RotationPresenter rotationPresenter;

    ActivityRotationBinding rotationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotationBinding =  DataBindingUtil.setContentView(this, R.layout.activity_rotation);
        rotationModel = new RotationModelImpl(this);
        rotationPresenter = new RotationPresenterImpl(this, rotationModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rotationPresenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        rotationPresenter.onStop();
    }

    @Override
    public void updatePosition(Position position) {
        rotationBinding.setPosition(position);
    }
}
