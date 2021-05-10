package com.example.drrive.service;

import com.example.drrive.model.Car;
import com.example.drrive.model.Company;
import com.example.drrive.model.Damage;
import com.example.drrive.model.Photo;
import com.example.drrive.model.Refueling;
import com.example.drrive.model.Services;
import com.example.drrive.model.User;
import com.example.drrive.model.UsersData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("login")
    Call<Void> login(@Body LoginRequest loginRequest);

    @GET("user")
    @Headers("Content-Type: application/json")
    Call<String> getCurrentUser();

    @GET("user/{username}")
    Call<User> getUserByUsername(@Path("username") String username);

    @GET("/user/{user}/usersdata")
    Call<UsersData> getUserDataByUserId(@Path("user") Integer user);

    @GET("car")
    Call<List<Car>> getCar();

    @GET("company")
    Call<Company> getCompany();

    @GET("{car}/damages")
    Call<List<Damage>> getDamage(@Path("car") Integer carId);

    @GET("/{damage}/photos")
    Call<List<Photo>> getPhotos(@Path("damage") Integer damageId);

    @GET("{car}/refuelings")
    Call<List<Refueling>> getRefuelings(@Path("car") Integer carId);

    @GET("{car}/services")
    Call<List<Services>> getServices(@Path("car") Integer carId);
}