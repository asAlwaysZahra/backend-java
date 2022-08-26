package com.mycompany.app.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
