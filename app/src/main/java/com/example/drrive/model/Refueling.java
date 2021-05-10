package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Refueling {

    @SerializedName("id_refuelings")
    private Integer idRefueling;
    @SerializedName("fuel_type")
    private String fuelType;
    @SerializedName("fuel_quantity")
    private Float fuelQuantity;
    @SerializedName("fuel_cost")
    private Float fuelCost;
    @SerializedName("mileage")
    private Integer mileage;
    @SerializedName("refuel_date")
    private Date date;
    private Integer carId;

    public Refueling() {
    }

    public Refueling(Integer idRefueling, String fuelType, Float fuelQuantity, Float fuelCost, Integer mileage, Date date, Integer carId) {
        this.idRefueling = idRefueling;
        this.fuelType = fuelType;
        this.fuelQuantity = fuelQuantity;
        this.fuelCost = fuelCost;
        this.mileage = mileage;
        this.date = date;
        this.carId = carId;
    }

    public Integer getIdRefueling() {
        return idRefueling;
    }

    public void setIdRefueling(Integer idRefueling) {
        this.idRefueling = idRefueling;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Float getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(Float fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public Float getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(Float fuelCost) {
        this.fuelCost = fuelCost;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
