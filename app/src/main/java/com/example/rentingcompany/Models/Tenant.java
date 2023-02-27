package com.example.rentingcompany.Models;

public class Tenant {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;
    private String confirmPassword;
    private String nationality;
    private String grossMonthlySalary;
    private String occupation;
    private String familySize;
    private String currentResidenceCountry;
    private String City;
    private String phoneNumber;

    public Tenant() {
    }

    public Tenant(String emailAddress, String firstName, String lastName, String gender, String password,
                  String confirmPassword, String nationality, String grossMonthlySalary, String occupation,
                  String familySize, String currentResidenceCountry, String city, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nationality = nationality;
        this.grossMonthlySalary = grossMonthlySalary;
        this.occupation = occupation;
        this.familySize = familySize;
        this.currentResidenceCountry = currentResidenceCountry;
        City = city;
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGrossMonthlySalary() {
        return grossMonthlySalary;
    }

    public void setGrossMonthlySalary(String grossMonthlySalary) {
        this.grossMonthlySalary = grossMonthlySalary;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getFamilySize() {
        return familySize;
    }

    public void setFamilySize(String familySize) {
        this.familySize = familySize;
    }

    public String getCurrentResidenceCountry() {
        return currentResidenceCountry;
    }

    public void setCurrentResidenceCountry(String currentResidenceCountry) {
        this.currentResidenceCountry = currentResidenceCountry;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "TenantData{" +
                "emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", nationality='" + nationality + '\'' +
                ", grossMonthlySalary='" + grossMonthlySalary + '\'' +
                ", occupation='" + occupation + '\'' +
                ", familySize='" + familySize + '\'' +
                ", currentResidenceCountry='" + currentResidenceCountry + '\'' +
                ", City='" + City + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
