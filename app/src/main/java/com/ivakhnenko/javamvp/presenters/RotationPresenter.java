package com.ivakhnenko.javamvp.presenters;

import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;
import com.ivakhnenko.javamvp.models.Position;

/**
 * Created by ruslan on 02.11.16.
 */

public interface RotationPresenter {

    void onStart();

    void onStop();

    void updatePosition(Position position);
}
