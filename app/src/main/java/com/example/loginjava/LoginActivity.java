 package com.example.loginjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView goRegister;
    EditText usernameLog, passwordLog;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLog = findViewById(R.id.usernameLog);
        passwordLog =  findViewById(R.id.passwordLog);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        goRegister = findViewById(R.id.goRegister);
        goRegister.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.loginBtn:
                LoginUser();
                break;
        }
    }

    private void LoginUser() {
        String username = usernameLog.getText().toString().trim();
        String password = passwordLog.getText().toString().trim();

        if (username.equals("") && password.equals("")) {
            Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
            //TODO Zmienic walidację przy zakladaniu konta na regex
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                //Starting Write and Read data with URL
                String[] field = new String[2];
                field[0] = "username";
                field[1] = "password";
                //Creating array for data
                String[] data = new String[2];
                data[0] = username;
                data[1] = password;
                PutData putData = new PutData("http://192.168.1.60/Drrive/login.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Login Success")) {
                            Toast.makeText(getApplicationContext(),"Welcome, " + username , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
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