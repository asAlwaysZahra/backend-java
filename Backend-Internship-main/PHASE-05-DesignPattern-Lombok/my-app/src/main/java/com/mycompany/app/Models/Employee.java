package com.mycompany.app.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private PersonalInformation personalInformation;
    private AccountInformation accountInformation;
    private int id;
    private String token;
    private int userType;
    private double creditBalance;
    private double myCash;
}
