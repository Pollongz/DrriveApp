package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("id_companies")
    private Integer idCompany;
    @SerializedName("name")
    private String name;
    @SerializedName("nip")
    private String nip;
    private Integer AddressId;

    public Company() {
    }

    public Company(Integer idCompany, String name, String nip, Integer addressId) {
        this.idCompany = idCompany;
        this.name = name;
        this.nip = nip;
        AddressId = addressId;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Integer getAddressId() {
        return AddressId;
    }

    public void setAddressId(Integer addressId) {
        AddressId = addressId;
    }
}
