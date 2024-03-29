package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("idCompany")
    private Integer idCompany;
    @SerializedName("name")
    private String name;
    @SerializedName("nip")
    private String nip;
    private Integer AddressId;
    private Address address;

    public Company() {
    }

    public Company(
            Integer idCompany,
            String name,
            String nip,
            Integer addressId,
            Address address
    ) {
        this.idCompany = idCompany;
        this.name = name;
        this.nip = nip;
        this.AddressId = addressId;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
