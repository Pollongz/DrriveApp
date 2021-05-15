package com.example.drrive.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("idPhoto")
    private Integer idPhoto;
    @SerializedName("photoUrl")
    private String photoUrl;
    private Integer damageId;

    public Photo() {
    }

    public Photo(Integer idPhoto, String photoUrl, Integer damageId) {
        this.idPhoto = idPhoto;
        this.photoUrl = photoUrl;
        this.damageId = damageId;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getDamageId() {
        return damageId;
    }

    public void setDamageId(Integer damageId) {
        this.damageId = damageId;
    }
}
