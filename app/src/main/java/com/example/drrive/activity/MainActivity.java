package com.example.drrive.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drrive.DateFormat;
import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Car;
import com.example.drrive.model.User;
import com.example.drrive.model.UsersData;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView myFullNameTv;
    private TextView todayDateView;
    private TextView myCompanyTv;
    private Button logoutBtn;
    private Button checkCarHistoryBtn;
    private Button fillCarReportBtn;
    private Button addRefuelBtn;
    private Button addDamageBtn;
    private Button checkServicesBtn;
    private String todaysDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFullNameTv = findViewById(R.id.myFullNameTv);
        todayDateView = findViewById(R.id.todaysDateTv);
        myCompanyTv = findViewById(R.id.myCompanyTv);
        logoutBtn = findViewById(R.id.logoutBtn);
        checkCarHistoryBtn = findViewById(R.id.checkCarHistoryBtn);
        fillCarReportBtn = findViewById(R.id.fillCarReportBtn);
        addRefuelBtn = findViewById(R.id.addRefuelBtn);
        addDamageBtn = findViewById(R.id.addDamageBtn);
        checkServicesBtn = findViewById(R.id.checkServicesBtn);

        getCurrentUser();

        todaysDate = DateFormat.getCurrentDate();
        todayDateView.setText(todaysDate);
        logoutBtn.setOnClickListener(this);
        checkCarHistoryBtn.setOnClickListener(this);
        fillCarReportBtn.setOnClickListener(this);
        addRefuelBtn.setOnClickListener(this);
        addDamageBtn.setOnClickListener(this);
        checkServicesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == logoutBtn) {
            SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLogged", false);
            editor.putString("token", "");
            editor.apply();

            Toast.makeText(getApplicationContext(), "User logged out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (v == checkCarHistoryBtn) {
            startActivity(new Intent(MainActivity.this, CarsHistoryActivity.class));
        } else if (v == fillCarReportBtn) {

        } else if (v == addRefuelBtn) {

        } else if (v == addDamageBtn) {

        } else if (v == checkServicesBtn) {

        }
    }

    // getting username from currently logged user
    public void getCurrentUser() {
        Call<String> call = ApiClient.getUserService(getApplicationContext()).getCurrentUser();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String user = response.body();
                    getUserByUsername(user);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }

    // getting user object from username returned from currently logged user
    public void getUserByUsername(String user) {
        Call<User> callUser = ApiClient.getUserService(getApplicationContext()).getUserByUsername(user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User thisUser = response.body();
                    Integer userId = thisUser.getIdUsers();// get User object nad then id of this user
                    getUsersDataByUserId(userId); // get UsersData object
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }

    // getting UsersData object from userId
    public void getUsersDataByUserId(Integer user) {
        Call<UsersData> callUsersData = ApiClient.getUserService(getApplicationContext()).getUserDataByUserId(user);
        callUsersData.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                if (response.isSuccessful()) {
                    UsersData thisUserData = response.body();
                    String userFullName = thisUserData.getFirstName() + " " + thisUserData.getLastName();
                    String companyInfo = thisUserData.getCompany().getName() + ", " + thisUserData.getCompany().getAddress().getCity();
                    myFullNameTv.setText(userFullName);
                    myCompanyTv.setText(companyInfo);

                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                myFullNameTv.setText(t.getMessage());
                myCompanyTv.setText(t.getMessage());
            }
        });
    }
}