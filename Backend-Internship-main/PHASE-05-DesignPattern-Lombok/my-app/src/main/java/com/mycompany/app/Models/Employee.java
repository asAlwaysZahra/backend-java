package com.mycompany.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private PersonalInformation personalInformation;
    private AccountInformation accountInformation;
    private int id;
    private String token;
    private int userType;
    private double creditBalance;
    private double myCash;
}
