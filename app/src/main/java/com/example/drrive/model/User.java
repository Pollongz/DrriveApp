package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("idUsers")
    private Integer idUsers;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("role")
    private String role;

    public User() {
    }

    public User(Integer idUsers, String username, String password, String email, String role) {
        this.idUsers = idUsers;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
