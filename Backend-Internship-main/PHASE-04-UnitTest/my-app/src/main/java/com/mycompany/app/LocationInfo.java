package com.mycompany.app;

import java.util.Objects;

public class LocationInfo {
    private double lng;
    private double lat;
    private String location;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationInfo that = (LocationInfo) o;
        return Double.compare(that.lng, lng) == 0
                && Double.compare(that.lat, lat) == 0
                && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lng, lat, location);
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
