package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("idAddress")
    private Integer idAddress;
    @SerializedName("country")
    private String country;
    @SerializedName("zipCode")
    private String zipCode;
    @SerializedName("city")
    private String city;
    @SerializedName("street")
    private String street;
    @SerializedName("buildingNumber")
    private String buildingNumber;

    public Address() {
    }

    public Address(Integer idAddress, String country, String zipCode, String city, String street, String buildingNumber) {
        this.idAddress = idAddress;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
