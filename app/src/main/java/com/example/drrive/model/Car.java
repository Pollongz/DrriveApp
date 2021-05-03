package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("carId")
    private int id_car_data;
    @SerializedName("carBrand")
    private String brand_name;
    @SerializedName("carModel")
    private String model_name;
    @SerializedName("carManufactureYear")
    private int manufacture_year;
    @SerializedName("type")
    private String type;
    @SerializedName("carCapacity")
    private Float engine_capacity;
    @SerializedName("carPower")
    private int engine_power;
    @SerializedName("carPlateNumber")
    private String plate_number;


    public Car() {
    }

    public Car(int id_car_data, String brand_name, String model_name, int manufacture_year, String type, Float engine_capacity, int engine_power, String plate_number) {
        this.id_car_data = id_car_data;
        this.brand_name = brand_name;
        this.model_name = model_name;
        this.manufacture_year = manufacture_year;
        this.type = type;
        this.engine_capacity = engine_capacity;
        this.engine_power = engine_power;
        this.plate_number = plate_number;
    }

    public int getId_car_data() {
        return id_car_data;
    }

    public void setId_car_data(int id_car_data) {
        this.id_car_data = id_car_data;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getManufacture_year() {
        return manufacture_year;
    }

    public void setManufacture_year(int manufacture_year) {
        this.manufacture_year = manufacture_year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(Float engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public int getEngine_power() {
        return engine_power;
    }

    public void setEngine_power(int engine_power) {
        this.engine_power = engine_power;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }
}
