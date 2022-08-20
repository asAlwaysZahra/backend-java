package com.mycompany.app;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalInformation that = (PersonalInformation) o;
        return gender == that.gender
                && Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && Objects.equals(password, that.password)
                && Objects.equals(about, that.about)
                && Objects.equals(country, that.country)
                && Objects.equals(locationInfo, that.locationInfo)
                && Objects.equals(dob, that.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, about, country, locationInfo, dob, gender);
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
