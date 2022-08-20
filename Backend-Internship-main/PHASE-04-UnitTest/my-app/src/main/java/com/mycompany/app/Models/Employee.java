package com.mycompany.app.Models;

public class Employee {

    private PersonalInformation personalInformation;
    private AccountInformation accountInformation;
    private final int id;
    private final String token;
    private final int userType;
    private final double creditBalance;
    private final double myCash;

    public Employee(PersonalInformation personalInformation, AccountInformation accountInformation,
                    int id, String token, int userType, double creditBalance, double myCash) {
        this.personalInformation = personalInformation;
        this.accountInformation = accountInformation;
        this.id = id;
        this.token = token;
        this.userType = userType;
        this.creditBalance = creditBalance;
        this.myCash = myCash;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "personalInformation=" + personalInformation +
                ", accountInformation=" + accountInformation +
                ", id=" + id +
                ", token='" + token + '\'' +
                ", userType=" + userType +
                ", creditBalance=" + creditBalance +
                ", myCash=" + myCash +
                '}';
    }

    // Getters and Setters ================================================

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public int getUserType() {
        return userType;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public double getMyCash() {
        return myCash;
    }
}
