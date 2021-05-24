package com.example.drrive.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.fragment.DamageFragment;
import com.example.drrive.fragment.ServiceFragment;
import com.example.drrive.model.Car;
import com.example.drrive.model.Damage;
import com.example.drrive.model.Post;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarHistoryActivity extends AppCompatActivity {

    private RadioButton DamageRb;
    private RadioButton ServiceRb;
    private Spinner chooseCarSpinner;
    private Integer companyId;
    private TextView chooseCarTv;
    private Integer carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_history);

        DamageRb = findViewById(R.id.DamageRb);
        ServiceRb = findViewById(R.id.ServiceRb);
        chooseCarTv = findViewById(R.id.chooseCarTv);
        chooseCarSpinner = findViewById(R.id.chooseCarSpinner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(
                getResources(),
                R.drawable.ic_arrow_back,
                getTheme()
        ));
        toolbar.setNavigationOnClickListener(v -> finish());

        SharedPreferences preferences = getSharedPreferences("logged", MODE_PRIVATE);
        companyId = preferences.getInt("companyId", 0);

        getAllCars(companyId);

        chooseCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DamageRb.setChecked(true);
                ServiceRb.setChecked(false);

                DamageFragment damageFragment = new DamageFragment();
                damageFragment.setArguments(getSelectedItem());

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, damageFragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private Bundle getSelectedItem() {
        Car car = (Car) chooseCarSpinner.getSelectedItem();
        carId = car.getIdCar();

        Bundle arguments = new Bundle();
        arguments.putInt("carId", carId);

        return arguments;
    }

    public void getAllCars(Integer idCompany) {
        Call<List<Car>> call = ApiClient.getUserService(getApplicationContext())
                .getCompanyCars(idCompany);
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful()) {
                    List<Car> carsList = response.body();

                    if (carsList != null) {

                        Car[] items = new Car[carsList.size()];

                        // Lopping through the whole list to get all the items
                        for (int i = 0; i < carsList.size(); i++) {
                            items[i] = carsList.get(i);
                        }

                        ArrayAdapter<Car> adapter;
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);

                        chooseCarSpinner.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }

    public void onRadioButtonClicked(@NonNull @NotNull View view) {

        // change fragment depending on which option is picked from menu
        switch (view.getId()) {
            case R.id.DamageRb:

                DamageFragment damageFragment = new DamageFragment();
                damageFragment.setArguments(getSelectedItem());

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, damageFragment).commit();
                break;
            case R.id.ServiceRb:

                ServiceFragment serviceFragment = new ServiceFragment();
                serviceFragment.setArguments(getSelectedItem());

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, serviceFragment).commit();
                break;
        }
    }
}