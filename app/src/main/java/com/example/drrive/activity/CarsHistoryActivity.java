package com.example.drrive.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.drrive.R;
import com.example.drrive.fragment.MainFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class CarsHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            Integer companyId = getIntent().getIntExtra("idCompany", 0);

            Bundle bundle = new Bundle();
            bundle.putInt("idCompany", companyId);
            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);

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
                Toast.makeText(this, "Wyloguj", Toast.LENGTH_SHORT).show();
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