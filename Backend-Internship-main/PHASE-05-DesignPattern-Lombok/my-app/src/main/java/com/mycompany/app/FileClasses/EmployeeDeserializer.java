package com.mycompany.app.FileClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mycompany.app.Models.AccountInformation;
import com.mycompany.app.Models.Employee;
import com.mycompany.app.Models.LocationInfo;
import com.mycompany.app.Models.PersonalInformation;

import java.io.IOException;

public class EmployeeDeserializer extends StdDeserializer<Employee> {

    public EmployeeDeserializer() {
        this(null);
    }

    public EmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Employee deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);

        // Personal Information -----------------------------------------------------
        String name = node.get("name").asText();
        String email = node.get("email").asText();
        String password = node.get("password").asText();
        String about = node.get("about").asText();
        String country = node.get("country").asText();
        String dob = node.get("dob").asText();
        int gender = (Integer) node.get("gender").numberValue();
        // location info
        String location = node.get("location").asText();
        double lng = node.get("lng").doubleValue();
        double lat = node.get("lat").doubleValue();
        LocationInfo loc = new LocationInfo(lng, lat, location);

        PersonalInformation personalInformation = PersonalInformation.builder()
                .name(name)
                .email(email)
                .password(password)
                .about(about)
                .country(country)
                .locationInfo(loc)
                .dob(dob)
                .gender(gender)
                .build();

        // Account Information -----------------------------------------------------
        int userStatus = (Integer) node.get("userStatus").numberValue();
        String profilePicture = node.get("profilePicture").asText();
        String coverPicture = node.get("coverPicture").asText();
        boolean enablefollowme = node.get("enablefollowme").asBoolean();
        boolean sendmenotifications = node.get("sendmenotifications").asBoolean();
        boolean sendTextmessages = node.get("sendTextmessages").asBoolean();
        boolean enabletagging = node.get("enabletagging").asBoolean();
        String createdAt = node.get("createdAt").asText();
        String updatedAt = node.get("updatedAt").asText();
        // live location info
        double livelng = node.get("livelng").doubleValue();
        double livelat = node.get("livelat").doubleValue();
        String liveLocation = node.get("liveLocation").asText();
        LocationInfo live = new LocationInfo(livelng, livelat, liveLocation);

        AccountInformation accountInformation = AccountInformation.builder()
                .userStatus(userStatus)
                .profilePicture(profilePicture)
                .coverPicture(coverPicture)
                .enablefollowme(enablefollowme)
                .sendmenotifications(sendmenotifications)
                .sendTextmessages(sendTextmessages)
                .enabletagging(enabletagging).createdAt(createdAt)
                .updatedAt(updatedAt)
                .liveLocationInfo(live)
                .build();

        // Employee Information -----------------------------------------------------
        int id = (Integer) node.get("id").numberValue();
        String token = node.get("token").asText();
        int userType = (Integer) node.get("userType").numberValue();
        double creditBalance = node.get("creditBalance").doubleValue();
        double myCash = node.get("myCash").doubleValue();

        return Employee.builder()
                .personalInformation(personalInformation)
                .accountInformation(accountInformation)
                .id(id)
                .token(token)
                .userType(userType)
                .creditBalance(creditBalance)
                .myCash(myCash)
                .build();
    }
}
