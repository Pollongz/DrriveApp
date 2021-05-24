package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class PlannedServices {

    @SerializedName("id_planned_services")
    private Integer idPlannedService;
    private String description;
    private String date;

    public PlannedServices() {
    }

    public PlannedServices(Integer idPlannedService, String description, String date) {
        this.idPlannedService = idPlannedService;
        this.description = description;
        this.date = date;
    }

    public Integer getIdPlannedService() {
        return idPlannedService;
    }

    public void setIdPlannedService(Integer idPlannedService) {
        this.idPlannedService = idPlannedService;
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
