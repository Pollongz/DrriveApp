package com.example.drrive.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("login")
    Call<Void> login(@Body LoginRequest loginRequest);

    @GET("secret")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}