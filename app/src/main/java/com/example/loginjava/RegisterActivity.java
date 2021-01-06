package com.example.loginjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailNew, passwordNew, passwordNew2, nameNew, surnameNew;
    private Button addUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        emailNew =  findViewById(R.id.emailNew);
        passwordNew =  findViewById(R.id.passwordNew);
        passwordNew2 =  findViewById(R.id.passwordNew2);
        nameNew =  findViewById(R.id.nameNew);
        surnameNew =  findViewById(R.id.surnameNew);

        addUser =  findViewById(R.id.addUser);
        addUser.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = emailNew.getText().toString().trim();
        String password = passwordNew.getText().toString().trim();
        String password2 = passwordNew2.getText().toString().trim();
        String name = nameNew.getText().toString().trim();
        String surname = surnameNew.getText().toString().trim();

        if (email.isEmpty()) {
            emailNew.setError("This field is required");
            emailNew.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailNew.setError("Invalid email address");
            emailNew.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            passwordNew.setError("Password must have at least 6 characters");
            passwordNew.requestFocus();
            return;
        }

        if (!password.equals(password2)) {
            passwordNew.setError("Passwords do not match");
            passwordNew.requestFocus();
            passwordNew2.setError("Passwords do not match");
            passwordNew2.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            nameNew.setError("This field is required");
            nameNew.requestFocus();
            return;
        }

        if (surname.isEmpty()) {
            surnameNew.setError("This field is required");
            surnameNew.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        User user = new User(email, name, surname);

                        FirebaseDatabase.getInstance("https://loginjava-23ea0-default-rtdb.firebaseio.com/").getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Something went wrong. Please try again later!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Something went wrong. Please try again later!", Toast.LENGTH_SHORT).show();
                    }

                });
    }
}