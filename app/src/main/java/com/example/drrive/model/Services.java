package com.example.drrive.model;

public class Services {

    private Integer idServices;
    private String description;
    private Float serviceCost;
    private Integer mileage;
    private String date;
    private Integer carId;

    public Services() {
    }

    public Services(Integer idServices, String description, Float serviceCost, Integer mileage, String date, Integer carId) {
        this.idServices = idServices;
        this.description = description;
        this.serviceCost = serviceCost;
        this.mileage = mileage;
        this.date = date;
        this.carId = carId;
    }

    public Integer getIdServices() {
        return idServices;
    }

    public void setIdServices(Integer idServices) {
        this.idServices = idServices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Float serviceCost) {
        this.serviceCost = serviceCost;
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
