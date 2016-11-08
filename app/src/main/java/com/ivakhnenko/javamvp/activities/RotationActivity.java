package com.ivakhnenko.javamvp.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ivakhnenko.javamvp.R;
import com.ivakhnenko.javamvp.databinding.ActivityRotationBinding;
import com.ivakhnenko.javamvp.models.Position;
import com.ivakhnenko.javamvp.models.RotationModel;
import com.ivakhnenko.javamvp.models.RotationModelImpl;
import com.ivakhnenko.javamvp.presenters.RotationPresenter;
import com.ivakhnenko.javamvp.presenters.RotationPresenterImpl;
import com.ivakhnenko.javamvp.views.RotationView;

import java.util.Arrays;

public class RotationActivity extends BaseLocationActivity implements RotationView, TextureView.SurfaceTextureListener {

    RotationModel rotationModel;

    RotationPresenter rotationPresenter;

    ActivityRotationBinding rotationBinding;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotationBinding = DataBindingUtil.setContentView(this, R.layout.activity_rotation);
        rotationModel = new RotationModelImpl(this);
        rotationPresenter = new RotationPresenterImpl(this, rotationModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rotationPresenter.onStart();

        TextureView textureView = (TextureView) findViewById(R.id.surfaceView);
        textureView.setSurfaceTextureListener(this);
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

    @Override
    public void onSurfaceTextureAvailable(final SurfaceTexture surface, final int width, final int height) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String[] cameraIdList = new String[0];
        try {
            cameraIdList = cameraManager.getCameraIdList();
            String frontCameraId = null;
            for (String camId : cameraIdList) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(camId);
                Integer frontCamera = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (frontCamera != null && frontCamera == CameraCharacteristics.LENS_FACING_FRONT) {
                    frontCameraId = camId;
                    break;
                }

            }
            if (frontCameraId != null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                cameraManager.openCamera(frontCameraId, new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(CameraDevice camera) {

                        surface.setDefaultBufferSize(width, height);
                        Surface surface1 = new Surface(surface);
                        try {
                            final CaptureRequest.Builder captureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                            captureRequest.addTarget(surface1);
                            camera.createCaptureSession(Arrays.asList(surface1), new CameraCaptureSession.StateCallback() {
                                @Override
                                public void onConfigured(CameraCaptureSession session) {
                                    try {
                                        session.setRepeatingRequest(captureRequest.build(), new CameraCaptureSession.CaptureCallback() {

                                        }, null);
                                    } catch (CameraAccessException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onConfigureFailed(CameraCaptureSession session) {

                                }
                            }, null);

                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onDisconnected(CameraDevice camera) {

                    }

                    @Override
                    public void onError(CameraDevice camera, int error) {

                    }
                }, null);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
