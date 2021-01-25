package com.example.loginjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView nameOfUserView,todayDateView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://loginjava-23ea0-default-rtdb.firebaseio.com/");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String userid;
    Date date=java.util.Calendar.getInstance().getTime();

    {
        assert currentUser != null;
        userid = currentUser.getUid();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameOfUserView = findViewById(R.id.nameOfUserView);
        todayDateView = findViewById(R.id.todayDateView);
        String todaysDate = date.toString();

        todayDateView.setText(todaysDate);

        reference.child("users").child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userName = snapshot.child("name").getValue(String.class);
                nameOfUserView.setText(userName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}