package com.ivakhnenko.javamvp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Surface;

import com.ivakhnenko.javamvp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CaptureActivity extends AppCompatActivity {

    MediaProjectionManager projectionManager;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        handler = new Handler();
        projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Intent screenCaptureIntent = projectionManager.createScreenCaptureIntent();
        startActivityForResult(screenCaptureIntent, 1001);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001){
            MediaProjection mediaProjection = projectionManager.getMediaProjection(resultCode, data);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            ImageReader imageReader = ImageReader.newInstance(dm.widthPixels, dm.heightPixels, PixelFormat.RGBA_8888, 2);
            Surface surface = imageReader.getSurface();
            mediaProjection.createVirtualDisplay("Ruslan Display", dm.widthPixels, dm.heightPixels, dm.densityDpi, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, surface, null, null);
            imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    try {
                        Image image = reader.acquireLatestImage();
                        if (image != null) {
                            Image.Plane[] planes = image.getPlanes();
                            int length = planes.length;
                            if (length > 0) {
                                Image.Plane plane = planes[0];
                                try {
                                    FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DCIM), String.format("%d.png", System.currentTimeMillis())));
                                    Bitmap bitmap = Bitmap.createBitmap(plane.getRowStride()/plane.getPixelStride(), image.getHeight(), Bitmap.Config.ARGB_8888);
                                    bitmap.copyPixelsFromBuffer(plane.getBuffer());
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
                                    image.close();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (IllegalStateException ex){
                        ex.printStackTrace();
                    }
                }
            }, handler);
        }
    }
}
