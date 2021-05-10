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

    private TextView nameOfUserView;
    private TextView todayDateView;
    private TextView getIt;
    private Button logoutBtn;
    private String todaysDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameOfUserView = findViewById(R.id.myFullNameTv);
        todayDateView = findViewById(R.id.todaysDateTv);
        getIt = findViewById(R.id.getIt);
        logoutBtn = findViewById(R.id.logoutBtn);


        getCurrentUser();
        todaysDate = DateFormat.getCurrentDate();
        todayDateView.setText(todaysDate);
        logoutBtn.setOnClickListener(this);

        Call<List<Car>> call = ApiClient.getUserService(getApplicationContext()).getCar();
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {

                if (response.isSuccessful()) {
                    List<Car> cars = response.body();
                    for (Car car : cars) {
                        getIt.setText(car.getModel_name());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                getIt.setText(t.getMessage());
            }
        });
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
                // something went wrong
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
                    Integer userId = thisUser.getIdUsers(); // get User object nad then id of this user
                    getUsersDataByUserId(userId); // get UsersData object
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // something went wrong
            }
        });
    }

    // getting UsersData object from userIdv
    public void getUsersDataByUserId(Integer user) {
        Call<UsersData> callUsersData = ApiClient.getUserService(getApplicationContext()).getUserDataByUserId(user);
        callUsersData.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                if (response.isSuccessful()) {
                    UsersData thisUserData = response.body();
                    String userFullName = thisUserData.getFirstName() + " " + thisUserData.getLastName();
                    nameOfUserView.setText(userFullName);
                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                nameOfUserView.setText(t.getMessage());
            }
        });
    }
}