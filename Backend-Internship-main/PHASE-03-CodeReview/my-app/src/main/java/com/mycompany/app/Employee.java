package com.mycompany.app;

public class Employee {

    private PersonalInformation personalInformation;
    private AccountInformation accountInformation;
    private int id;
    private String token;
    private int userType;
    private double creditBalance;
    private double myCash;

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

    // Getters and Setters ================================================

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public double getMyCash() {
        return myCash;
    }

    public void setMyCash(double myCash) {
        this.myCash = myCash;
    }
}
