package com.mycompany.app;

public class LocationInfo {
    private double lng;
    private double lat;
    private String location;

    public LocationInfo(double lng, double lat, String location) {
        this.lng = lng;
        this.lat = lat;
        this.location = location;
    }

    // Getters and Setters ================================================

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
