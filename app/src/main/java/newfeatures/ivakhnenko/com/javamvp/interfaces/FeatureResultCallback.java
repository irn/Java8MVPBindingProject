package newfeatures.ivakhnenko.com.javamvp.interfaces;

import android.support.annotation.Nullable;

/**
 * Created by Ruslan Ivakhnenko on 27.09.16.
 */

public interface FeatureResultCallback<T> {

    void onResult(@Nullable T result);
}
