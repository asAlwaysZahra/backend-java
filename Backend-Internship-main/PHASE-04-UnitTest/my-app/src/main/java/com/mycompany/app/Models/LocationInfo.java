package com.mycompany.app.Models;

public class LocationInfo {
    private final double lng;
    private final double lat;
    private final String location;

    public LocationInfo(double lng, double lat, String location) {
        this.lng = lng;
        this.lat = lat;
        this.location = location;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "lng=" + lng +
                ", lat=" + lat +
                ", location='" + location + '\'' +
                '}';
    }

    // Getters and Setters ================================================

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getLocation() {
        return location;
    }
}
