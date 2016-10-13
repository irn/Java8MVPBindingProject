package com.ivakhnenko.javamvp.models;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.ivakhnenko.javamvp.interfaces.FeatureResultCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan Ivakhnenko on 27.09.16.
 */

public class PlaceModelImpl implements PlaceModel, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Activity context;

    private GoogleApiClient googleApiClient;

    public PlaceModelImpl(Activity activity) {
        this.context = activity;
        googleApiClient = new GoogleApiClient.Builder(this.context)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void getCurrentPlace(final FeatureResultCallback<Place> result) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            Places.PlaceDetectionApi.getCurrentPlace(googleApiClient, null).setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                    if (placeLikelihoods.getStatus().isSuccess())
                        result.onResult(placeLikelihoods.get(0).getPlace());
                }
            });

        }
    }

    @Override
    public void getPlaceById(String id, final FeatureResultCallback<Place> result) {
        PendingResult<PlaceBuffer> placeById = Places.GeoDataApi.getPlaceById(googleApiClient, id);
        placeById.setResultCallback(new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(@NonNull PlaceBuffer places) {
                if (places.getStatus().isSuccess()){
                    result.onResult(places.get(0));
                }
            }
        });
    }

    @Override
    public void getPlacePhotos(final String id, final FeatureResultCallback<List<Bitmap>> photoResult) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                PlacePhotoMetadataResult metadataResult = Places.GeoDataApi.getPlacePhotos(googleApiClient, id).await();
                List<Bitmap> bitmapList = new ArrayList<>();
                if (metadataResult.getStatus().isSuccess()){
                    for (PlacePhotoMetadata photoMetadata : metadataResult.getPhotoMetadata()) {
                        PlacePhotoResult photoResult1 = photoMetadata.getPhoto(googleApiClient).await();
                        if (photoResult1.getStatus().isSuccess()){
                            bitmapList.add(photoResult1.getBitmap());
                        }
                    }
                }
                metadataResult.getPhotoMetadata().release();
                photoResult.onResult(bitmapList);
            }
        });


    }

    @Override
    public void onStart() {
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
