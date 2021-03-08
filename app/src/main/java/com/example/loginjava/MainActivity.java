package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView nameOfUserView,todayDateView;
    Date date=java.util.Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameOfUserView = findViewById(R.id.nameOfUserView);
        todayDateView = findViewById(R.id.todayDateView);
        String todaysDate = date.toString();

        todayDateView.setText(todaysDate);

    }
}