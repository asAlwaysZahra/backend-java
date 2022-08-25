package com.mycompany.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalInformation {
    private String name;
    private String email;
    private String password;
    private String about;
    private String country;
    private LocationInfo locationInfo;
    private String dob;
    private int gender;
}
