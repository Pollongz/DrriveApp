package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Damage {

    @SerializedName("id_damages")
    private Integer idDamage;
    @SerializedName("description")
    private String description;
    private String date;
    private Integer carId;
    private Integer reportedById;
    private UsersData reportedBy;

    public Damage() {
    }

    public Damage(Integer idDamage, String description, String date, Integer carId, Integer reportedById, UsersData reportedBy) {
        this.idDamage = idDamage;
        this.description = description;
        this.date = date;
        this.carId = carId;
        this.reportedById = reportedById;
        this.reportedBy = reportedBy;
    }

    public Integer getIdDamage() {
        return idDamage;
    }

    public void setIdDamage(Integer idDamage) {
        this.idDamage = idDamage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getReportedById() {
        return reportedById;
    }

    public void setReportedById(Integer reportedById) {
        this.reportedById = reportedById;
    }

    public UsersData getUsersData() {
        return reportedBy;
    }

    public void setUsersData(UsersData reportedBy) {
        this.reportedBy = reportedBy;
    }
}
