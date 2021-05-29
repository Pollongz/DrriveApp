package com.example.drrive.model;

public class Report {

    private Integer idReport;
    private Integer mileage;
    private String dateTime;
    private Integer idUsersData;
    private Integer idCar;

    public Report() {
    }

    public Report(Integer idReport, Integer mileage, String dateTime, Integer idUsersData, Integer idCar) {
        this.idReport = idReport;
        this.mileage = mileage;
        this.dateTime = dateTime;
        this.idUsersData = idUsersData;
        this.idCar = idCar;
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getIdUsersData() {
        return idUsersData;
    }

    public void setIdUsersData(Integer idUsersData) {
        this.idUsersData = idUsersData;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }
}
