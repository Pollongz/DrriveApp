package com.example.drrive.service;

import com.example.drrive.model.Car;
import com.example.drrive.model.Company;
import com.example.drrive.model.Damage;
import com.example.drrive.model.Photo;
import com.example.drrive.model.PlannedServices;
import com.example.drrive.model.Post;
import com.example.drrive.model.Refueling;
import com.example.drrive.model.Report;
import com.example.drrive.model.Services;
import com.example.drrive.model.User;
import com.example.drrive.model.UsersData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("login")
    Call<Void> login(@Body LoginRequest loginRequest);

    @POST("refueling")
    Call<Void> addNewRefueling(@Body Refueling refueling);

    @POST("reports")
    Call<Void> addNewReport(@Body Report report);

    @POST("damage")
    Call<Void> addNewDamage(@Body Damage damage);

    @POST("service")
    Call<Void> addNewServices(@Body Services services);

    @GET("user")
    @Headers("Content-Type: application/json")
    Call<String> getCurrentUser();

    @GET("user/{username}")
    Call<User> getUserByUsername(@Path("username") String username);

    @GET("/user/{user}/usersdata")
    Call<UsersData> getUserDataByUserId(@Path("user") Integer user);

    @GET("/company/{company}/posts")
    Call<List<Post>> getCompanyPosts(@Path("company") Integer company);

    @GET("/company/{company}/cars")
    Call<List<Car>> getCompanyCars(@Path("company") Integer company);

    @GET("car/{car}/damages")
    Call<List<Damage>> getCarsDamages(@Path("car") Integer carId);

    @GET("/{damage}/photos")
    Call<List<Photo>> getPhotos(@Path("damage") Integer damageId);

    @GET("car/{car}/services")
    Call<List<Services>> getCarsServices(@Path("car") Integer carId);

    @GET("car/{car}/planned")
    Call<List<PlannedServices>> getCarsPlannedServices(@Path("car") Integer carId);

    @DELETE("planned/{id}")
    Call<Void> deletePlannedServices(@Path("id") int plannedId);

}