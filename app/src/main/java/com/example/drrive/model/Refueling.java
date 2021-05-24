package com.example.drrive.model;

public class Refueling {

    private Integer idRefueling;
    private String fuelType;
    private Float fuelQuantity;
    private Float fuelCost;
    private Integer mileage;
    private String date;
    private Integer carId;

    public Refueling() {
    }

    public Refueling(Integer idRefueling, String fuelType, Float fuelQuantity, Float fuelCost, Integer mileage, String date, Integer carId) {
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
}
