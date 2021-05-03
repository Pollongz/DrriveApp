 package com.example.drrive.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.service.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameLog;
    private EditText passwordLog;
    private Button loginBtn;
    private String token;
    private Boolean isLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLog = findViewById(R.id.usernameLog);
        passwordLog =  findViewById(R.id.passwordLog);
        loginBtn = findViewById(R.id.loginBtn);

        SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
        isLogged = preferences.getBoolean("isLogged", false);
        preferences.getString("token", "");

        if (isLogged) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Log in to continue", Toast.LENGTH_SHORT).show();
        }

        loginBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v == loginBtn) {
            //TODO check if credentials correctly entered
            login();
        }
    }

    private void login() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(usernameLog.getText().toString().trim());
        loginRequest.setPassword(passwordLog.getText().toString().trim());

        Call<Void> loginResponseCall = ApiClient.getUserService().login(loginRequest);
        loginResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    token = response.headers().get("Authorization");

                    SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLogged", true);
                    editor.putString("token", token);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "User logged in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "User failed logging in: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
 }