package com.example.drrive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Services;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkServiceAsDoneFragment extends Fragment {

    public static final String SERVICE_ID = "service_id";
    public static final String CAR_ID = "car_id";
    private static final String SERVICE_DESCRIPTION = "description";

    private EditText mileageEt;
    private EditText serviceCostEt;
    private Button addServiceBtn;
    private ProgressBar progressBar;
    private int carId;
    private int serviceId;
    private String serviceDescription;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_mark_service_as_done, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            carId = bundle.getInt(CAR_ID, 1);
            serviceId = bundle.getInt(SERVICE_ID, 1);
            serviceDescription = bundle.getString(SERVICE_DESCRIPTION, "desc");
        }

        mileageEt = view.findViewById(R.id.mileageEt);
        serviceCostEt = view.findViewById(R.id.serviceCostEt);
        addServiceBtn = view.findViewById(R.id.addServiceBtn);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        
        addServiceBtn.setOnClickListener(view1 -> {
            progressBar.setVisibility(View.VISIBLE);
            addDoneServiceAndDeleteFromPlanned(serviceDescription, serviceId, carId);
        });
        
        return view;
    }
    
    private void addDoneServiceAndDeleteFromPlanned(String description, int serviceId, int carId) {
        Services services = getServices(description, carId);

        Call<Void> newServiceResponseCall = ApiClient.getUserService(getActivity()).addNewServices(services);
        newServiceResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    deletePlannedServiceFromList(serviceId);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Wystąpił błąd: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    private Services getServices(String description, int carId) {
        Services services = new Services();
        services.setCarId(carId);
        services.setServiceCost(Float.parseFloat(serviceCostEt.getText().toString().trim()));
        services.setMileage(Integer.parseInt(mileageEt.getText().toString().trim()));
        services.setDescription(description);

        String date = LocalDate.now().toString();
        String serviceDate = date.replace("-",".");

        services.setDate(serviceDate);

        return services;
    }

    private void deletePlannedServiceFromList(int serviceId) {
        Call<Void> removeServiceResponseCall = ApiClient.getUserService(getActivity()).deletePlannedServices(serviceId);
        removeServiceResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PlannedServicesFragment()).commit();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //TODO smth
            }
        });
    }
}
