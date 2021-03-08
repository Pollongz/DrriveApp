package com.example.loginjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText passwordNew, passwordNew2, usernameNew, emailNew;
    Spinner privelageNew;
    public  Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        passwordNew =  findViewById(R.id.passwordNew);
        passwordNew2 =  findViewById(R.id.passwordNew2);
        usernameNew =  findViewById(R.id.usernameNew);
        emailNew =  findViewById(R.id.emailNew);
        addUser =  findViewById(R.id.addUser);
        privelageNew = findViewById(R.id.spinnerPrivelage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.privelages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privelageNew.setAdapter(adapter);

        addUser.setOnClickListener(v -> registerUser());
    }

    public void registerUser() {

        String password = passwordNew.getText().toString().trim();
        String password2 = passwordNew2.getText().toString().trim();
        String username = usernameNew.getText().toString().trim();
        String email = emailNew.getText().toString().trim();
        String privelage = privelageNew.getSelectedItem().toString().trim();

            if (password.equals("") && password2.equals("") && username.equals("") && email.equals("") && privelage.equals("")) {
            Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
            } else {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[4];
                    field[0] = "username";
                    field[1] = "password";
                    field[2] = "email";
                    field[3] = "privelage";
                    //Creating array for data
                    String[] data = new String[4];
                    data[0] = username;
                    data[1] = password;
                    data[2] = email;
                    data[3] = privelage;
                    PutData putData = new PutData("http://192.168.1.60/drrive/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")) {
                                Toast.makeText(getApplicationContext(),"User registered succesfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(),"An error occured. Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                });
        }
    }
}