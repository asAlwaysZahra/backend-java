package com.mycompany.app;

public class PersonalInformation {
    private String name;
    private String email;
    private String password;
    private String about;
    private String country;
    private LocationInfo locationInfo;
    private String dob;
    private int gender;

    public PersonalInformation(String name, String email, String password, String about,
                               String country, LocationInfo locationInfo, String dob, int gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.country = country;
        this.locationInfo = locationInfo;
        this.dob = dob;
        this.gender = gender;
    }

    // Getters and Setters ================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocationInfo getLocation() {
        return locationInfo;
    }

    public void setLocation(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
