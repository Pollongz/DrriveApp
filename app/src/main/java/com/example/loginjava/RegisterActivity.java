package com.example.loginjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailNew, passwordNew, passwordNew2, usernameNew, fullnameNew;
    public  Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailNew =  findViewById(R.id.emailNew);
        passwordNew =  findViewById(R.id.passwordNew);
        passwordNew2 =  findViewById(R.id.passwordNew2);
        usernameNew =  findViewById(R.id.usernameNew);
        fullnameNew =  findViewById(R.id.fullnameNew);

        addUser =  findViewById(R.id.addUser);

        addUser.setOnClickListener(v -> {
            String email = emailNew.getText().toString().trim();
            String password = passwordNew.getText().toString().trim();
            String password2 = passwordNew2.getText().toString().trim();
            String username = usernameNew.getText().toString().trim();
            String fullname = fullnameNew.getText().toString().trim();

            if (email.equals("") && password.equals("") && password2.equals("") && username.equals("") && fullname.equals("")) {
                Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                //TODO Zmienic walidację przy zakladaniu konta na regex
            } else if (email.length() < 3 || email.length() > 64) {
                emailNew.setError("Password must be from 6 to 32 characters");
                emailNew.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailNew.setError("Invalid email address");
                emailNew.requestFocus();
            } else if (password.length() < 6 || password.length() > 32) {
                passwordNew.setError("Password must have minimum 8 and maximum 32 characters");
                passwordNew.requestFocus();
            } else if (!password.equals(password2)){
                passwordNew.setError("Passwords do not match");
                passwordNew.requestFocus();
                passwordNew2.setError("Passwords do not match");
                passwordNew2.requestFocus();
            } else if (username.length() < 3 || username.length() > 32) {
                usernameNew.setError("Username must have minimum 6 and maximum 24 characters");
                usernameNew.requestFocus();
            } else if (fullname.length() < 6 || fullname.length() > 64) {
                fullnameNew.setError("Fullname must be from 6 to 64 characters");
                fullnameNew.requestFocus();
            } else {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    String[] field = new String[4];
                    field[0] = "username";
                    field[1] = "password";
                    field[2] = "fullname";
                    field[3] = "email";
                    //Creating array for data
                    String[] data = new String[4];
                    data[0] = username;
                    data[1] = password;
                    data[2] = fullname;
                    data[3] = email;
                    PutData putData = new PutData("http://192.168.1.60/Drrive/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")) {
                                Toast.makeText(getApplicationContext(),"Account registered successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(),"An error occured. Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                });
            }
        });
    }
}