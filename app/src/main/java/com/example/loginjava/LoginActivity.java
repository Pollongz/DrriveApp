 package com.example.loginjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView goRegister;
    private EditText emailLog, passwordLog;
    private Button loginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLog = findViewById(R.id.emailLog);
        passwordLog =  findViewById(R.id.passwordLog);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        goRegister = findViewById(R.id.goRegister);
        goRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
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
        String email = emailLog.getText().toString().trim();
        String password = passwordLog.getText().toString().trim();

        if (email.isEmpty()) {
            emailLog.setError("This field is required");
            emailLog.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordLog.setError("This field is required");
            passwordLog.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong email or password. Please try again!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}