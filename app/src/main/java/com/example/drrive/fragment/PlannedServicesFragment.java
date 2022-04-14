package com.example.drrive.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Car;
import com.example.drrive.model.PlannedServices;
import com.example.drrive.recyclerview.RecyclerPlannedServicesAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class PlannedServicesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerPlannedServicesAdapter recyclerPlannedServicesAdapter;
    private Spinner chooseCarSpinner;
    private Integer companyId;
    private Integer carId;

    public PlannedServicesFragment() {
    }

    @Nullable

    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_planned_services, container, false);

        chooseCarSpinner = view.findViewById(R.id.chooseCarSpinner);
        recyclerView = view.findViewById(R.id.plannedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerPlannedServicesAdapter = new RecyclerPlannedServicesAdapter();

        SharedPreferences preferences = requireActivity().getSharedPreferences("logged", MODE_PRIVATE);
        companyId = preferences.getInt("companyId", 0);

        getAllCars(companyId);

        chooseCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Car car = (Car) chooseCarSpinner.getSelectedItem();

                if (car != null) {
                    carId = car.getIdCar();
                    getAllPlannedServices(carId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    public void getAllCars(Integer idCompany) {
        Call<List<Car>> call = ApiClient.getUserService(getActivity())
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

                        ArrayAdapter<Car> adapter = new ArrayAdapter<>(
                                getActivity(),
                                R.layout.spinner_item,
                                items
                        );

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

    public void getAllPlannedServices(Integer idCar) {

        Call<List<PlannedServices>> call = ApiClient.getUserService(getContext()).getCarsPlannedServices(idCar);
        call.enqueue(new Callback<List<PlannedServices>>() {
            @Override
            public void onResponse(Call<List<PlannedServices>> call, Response<List<PlannedServices>> response) {
                if (response.isSuccessful()) {
                    List<PlannedServices> damagesList = response.body();

                    recyclerPlannedServicesAdapter.setData(damagesList);
                    recyclerView.setAdapter(recyclerPlannedServicesAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<PlannedServices>> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }
}
