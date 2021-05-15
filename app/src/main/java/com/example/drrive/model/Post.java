package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post {

    @SerializedName("idPost")
    private Integer idPost;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("date")
    private String date;
    private Integer companyId;

    public Post() {
    }

    public Post(Integer idPost, String title, String description, String date, Integer companyId) {
        this.idPost = idPost;
        this.title = title;
        this.description = description;
        this.date = date;
        this.companyId = companyId;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
