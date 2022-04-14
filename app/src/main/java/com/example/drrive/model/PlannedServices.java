package com.example.drrive.model;

public class PlannedServices {

    private Integer idPlannedService;
    private Integer carId;
    private String description;
    private String date;
    private Car car;

    public PlannedServices() {
    }

    public PlannedServices(Integer idPlannedService, Integer carId, String description, String date, Car car) {
        this.idPlannedService = idPlannedService;
        this.carId = carId;
        this.description = description;
        this.date = date;
        this.car = car;
    }

    public Integer getIdPlannedService() {
        return idPlannedService;
    }

    public void setIdPlannedService(Integer idPlannedService) {
        this.idPlannedService = idPlannedService;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getcarId() {
        return carId;
    }

    public void setcarId(Integer carId) {
        this.carId = carId;
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
}
