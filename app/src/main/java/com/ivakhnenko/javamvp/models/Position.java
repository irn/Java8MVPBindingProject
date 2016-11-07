package com.ivakhnenko.javamvp.models;

/**
 * Created by ruslan on 06.11.16.
 */

public class Position {

    /**
     * the bearing between current location and target
     */
    private float locationBearing;

    /**
     * The angle from azimuth in degree
     */
    private double deviceAzimuth;


    /**
     * distance to target location in meter
     */
    private int distance;

    public float getLocationBearing() {
        return locationBearing;
    }

    public void setLocationBearing(float locationBearing) {
        this.locationBearing = locationBearing;
    }

    public double getDeviceAzimuth() {
        return deviceAzimuth;
    }

    public void setDeviceAzimuth(double deviceAzimuth) {
        this.deviceAzimuth = deviceAzimuth;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
