package com.example.rentingcompany.Models;

public class RentingAgency {

    private String emailAddress;
    private String agencyName;
    private String password;
    private String confirmPassword;
    private String country;
    private String city;
    private String phoneNumber;


    public RentingAgency() {

    }

    public RentingAgency(String emailAddress, String agencyName,
                         String password, String confirmPassword,
                         String country, String city, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.agencyName = agencyName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "RentingAgencyData{" +
                "emailAddress='" + emailAddress + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
