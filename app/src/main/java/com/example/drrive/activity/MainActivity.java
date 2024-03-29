package com.example.drrive.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.drrive.R;
import com.example.drrive.fragment.AddDamageFragment;
import com.example.drrive.fragment.FillReportFragment;
import com.example.drrive.fragment.PlannedServicesFragment;
import com.example.drrive.fragment.RefuelingFragment;
import com.example.drrive.service.InternetCheck;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InternetCheck internetCheck = new InternetCheck(MainActivity.this);
        boolean connection = internetCheck.isNetworkAvailable(getApplicationContext());

        if (!connection) {
            internetCheck.connectionDialog(MainActivity.this);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.drrive.fragment.MainFragment()).commit();
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
                startActivity(new Intent(MainActivity.this, CarHistoryActivity.class));
                break;
            case R.id.nav_fill_car_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FillReportFragment()).commit();
                break;
            case R.id.nav_refueling:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RefuelingFragment()).commit();
                break;
            case R.id.nav_services:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlannedServicesFragment()).commit();
                break;
            case R.id.nav_damages:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddDamageFragment()).commit();
                break;
            case R.id.nav_logout:

                SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogged", false);
                editor.putString("token", "");
                editor.putInt("companyId", 0);
                editor.putInt("userDataId", 0);
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
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Kliknij ponownie, aby wyjść", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
        }
    }
}