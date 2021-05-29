package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("idCar")
    private Integer idCar;
    @SerializedName("carBrand")
    private String carBrand;
    @SerializedName("carModel")
    private String carModel;
    @SerializedName("carManufactureYear")
    private Integer carManufactureYear;
    @SerializedName("carType")
    private String carType;
    @SerializedName("engineCapacity")
    private String engineCapacity;
    @SerializedName("enginePowe")
    private String enginePower;
    @SerializedName("plateNumber")
    private String plateNumber;
    @SerializedName("isTaken")
    private Integer isTaken;
    @SerializedName("CompanyId")
    private Integer CompanyId;
    @SerializedName("company")
    private Company company;
    @SerializedName("insurance")
    private Insurance insurance;

    public Car() {
    }

    public Car(
            Integer idCar,
            String carBrand,
            String carModel,
            Integer carManufactureYear,
            String carType,
            String engineCapacity,
            String enginePower,
            String plateNumber,
            Integer isTaken,
            Integer companyId,
            Company company,
            Insurance insurance) {
        this.idCar = idCar;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carManufactureYear = carManufactureYear;
        this.carType = carType;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
        this.plateNumber = plateNumber;
        this.isTaken = isTaken;
        this.CompanyId = companyId;
        this.company = company;
        this.insurance = insurance;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getCarManufactureYear() {
        return carManufactureYear;
    }

    public void setCarManufactureYear(Integer carManufactureYear) {
        this.carManufactureYear = carManufactureYear;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(Integer isTaken) {
        this.isTaken = isTaken;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return carBrand +
                " " + carModel +
                " " + engineCapacity +
                " - " + plateNumber;
    }
}
