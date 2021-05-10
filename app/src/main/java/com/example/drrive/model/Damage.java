package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Damage {

    @SerializedName("id_damages")
    private Integer idDamage;
    @SerializedName("description")
    private String description;
    private Integer carId;

    public Damage() {
    }

    public Damage(Integer idDamage, String description, Integer carId) {
        this.idDamage = idDamage;
        this.description = description;
        this.carId = carId;
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

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
