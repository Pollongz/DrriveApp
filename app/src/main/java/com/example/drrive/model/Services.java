package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Services {

    @SerializedName("id_services")
    private Integer idServices;
    @SerializedName("service_type")
    private String serviceType;
    @SerializedName("service_cost")
    private Float ServiceCost;
    @SerializedName("mileage")
    private Integer mileage;
    @SerializedName("service_date")
    private Date date;
    private Integer carId;

    public Services() {
    }

    public Services(Integer idServices, String serviceType, Float serviceCost, Integer mileage, Date date, Integer carId) {
        this.idServices = idServices;
        this.serviceType = serviceType;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
