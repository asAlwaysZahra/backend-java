package com.mycompany.app.Models;

public class PersonalInformation {
    private final String name;
    private final String email;
    private final String password;
    private final String about;
    private final String country;
    private final LocationInfo locationInfo;
    private final String dob;
    private final int gender;

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

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", country='" + country + '\'' +
                ", locationInfo=" + locationInfo +
                ", dob='" + dob + '\'' +
                ", gender=" + gender +
                '}';
    }

    // Getters and Setters ================================================
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    public String getCountry() {
        return country;
    }

    public LocationInfo getLocation() {
        return locationInfo;
    }

    public String getDob() {
        return dob;
    }

    public int getGender() {
        return gender;
    }
}
