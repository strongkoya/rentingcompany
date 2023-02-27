package com.example.rentingcompany.Models;

public class Property {
    private String postalAddress;
    private String city;
    private int surfaceArea;
    private int constructionYear;
    private int numOfBedrooms;
    private double rentalPrice;
    private boolean isFurnished;
    private String photoURL;
    private String availabilityDate;
    private String descryption;

    public Property() {
    }

    public Property(String postalAddress, String city, int surfaceArea, int constructionYear, int numOfBedrooms, double rentalPrice, boolean isFurnished, String photoURL, String availabilityDate, String descryption) {
        this.postalAddress = postalAddress;
        this.city = city;
        this.surfaceArea = surfaceArea;
        this.constructionYear = constructionYear;
        this.numOfBedrooms = numOfBedrooms;
        this.rentalPrice = rentalPrice;
        this.isFurnished = isFurnished;
        this.photoURL = photoURL;
        this.availabilityDate = availabilityDate;
        this.descryption = descryption;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(int surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(String availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }
}
