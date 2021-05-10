package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class UsersData {

    @SerializedName("idUserData")
    private Integer idUserData;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("user")
    private User user;
    @SerializedName("companyId")
    private Integer companyId;
    @SerializedName("company")
    private Company company;

    public UsersData() {
    }

    public UsersData(Integer idUserData, String firstName, String lastName, String phoneNumber, Integer userId, User user, Integer companyId, Company company) {
        this.idUserData = idUserData;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.user = user;
        this.companyId = companyId;
        this.company = company;
    }

    public Integer getIdUserData() {
        return idUserData;
    }

    public void setIdUserData(Integer idUserData) {
        this.idUserData = idUserData;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
