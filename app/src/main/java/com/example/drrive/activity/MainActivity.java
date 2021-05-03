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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameOfUserView;
    private TextView todayDateView;
    private Button logoutBtn;
    private String todaysDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameOfUserView = findViewById(R.id.myFullNameTv);
        todayDateView = findViewById(R.id.todaysDateTv);
        logoutBtn = findViewById(R.id.logoutBtn);

        todaysDate = DateFormat.getCurrentDate();
        todayDateView.setText(todaysDate);

        logoutBtn.setOnClickListener(this);
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
}