package com.example.drrive.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drrive.DateFormat;
import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.fragment.CarHistoryFragment;
import com.example.drrive.fragment.MainFragment;
import com.example.drrive.model.Car;
import com.example.drrive.model.Company;
import com.example.drrive.model.User;
import com.example.drrive.model.UsersData;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get usersdata
        //getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

//            Integer companyId = getIntent().getIntExtra("idCompany", 0);

//            Bundle bundle = new Bundle();
//            bundle.putInt("idCompany", companyId);
            MainFragment mainFragment = new MainFragment();
//            mainFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    mainFragment).commit();
            navigationView.setCheckedItem(R.id.nav_notifications);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        // change fragment depending on which option is picked from menu
        switch (item.getItemId()) {
            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.example.drrive.fragment.MainFragment()).commit();
                break;
            case R.id.nav_car_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.example.drrive.fragment.CarHistoryFragment()).commit();
                break;
            case R.id.nav_logout:

                SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogged", false);
                editor.putString("token", "");
                editor.apply();

                Toast.makeText(getApplicationContext(), "User logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}