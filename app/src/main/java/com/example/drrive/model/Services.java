package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Services {

    @SerializedName("id_services")
    private Integer idServices;
    @SerializedName("description")
    private String description;
    @SerializedName("service_cost")
    private Float ServiceCost;
    @SerializedName("mileage")
    private Integer mileage;
    private String date;
    private Integer carId;

    public Services() {
    }

    public Services(Integer idServices, String description, Float serviceCost, Integer mileage, String date, Integer carId) {
        this.idServices = idServices;
        this.description = description;
        ServiceCost = serviceCost;
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
        return ServiceCost;
    }

    public void setServiceCost(Float serviceCost) {
        ServiceCost = serviceCost;
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
