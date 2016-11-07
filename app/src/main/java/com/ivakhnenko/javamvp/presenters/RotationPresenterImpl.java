package com.ivakhnenko.javamvp.presenters;

import android.support.annotation.Nullable;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.models.Position;
import com.ivakhnenko.javamvp.models.RotationModel;
import com.ivakhnenko.javamvp.views.RotationView;

/**
 * Created by ruslan on 06.11.16.
 */

public class RotationPresenterImpl implements RotationPresenter, FeatureResultCallback<Position> {

    RotationView rotationView;

    RotationModel rotationModel;

    public RotationPresenterImpl(RotationView rotationView, RotationModel model) {
        this.rotationView = rotationView;
        rotationModel = model;
    }

    @Override
    public void onStart() {
        rotationModel.onStart();
        rotationModel.setDirectionChanged(this);
    }

    @Override
    public void onStop() {
        rotationModel.setDirectionChanged(null);
        rotationModel.onStop();
    }

    @Override
    public void updatePosition(Position position) {
        rotationView.updatePosition(position);
    }

    @Override
    public void onResult(@Nullable Position result) {
        updatePosition(result);
    }
}
