package com.example.drrive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Services;
import com.example.drrive.recyclerview.RecyclerServiceAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceFragment extends Fragment {

    private TextView serviceDescriptionTv;
    private TextView serviceDateTv;
    private RecyclerView serviceRecyclerView;
    private RecyclerServiceAdapter recyclerServiceAdapter;
    private Integer carId;

    @Nullable

    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_car_history_services, container, false);

        serviceDescriptionTv = view.findViewById(R.id.serviceDescriptionTv);
        serviceDateTv = view.findViewById(R.id.serviceDateTv);

        serviceRecyclerView = view.findViewById(R.id.serviceRecyclerView);
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerServiceAdapter = new RecyclerServiceAdapter();

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            carId = bundle.getInt("carId" , -1);
            getAllServices(carId);
        }

        return view;
    }

    public void getAllServices(Integer idCar) {
        Call<List<Services>> call = ApiClient.getUserService(getContext()).getCarsServices(idCar);
        call.enqueue(new Callback<List<Services>>() {
            @Override
            public void onResponse(Call<List<Services>> call, Response<List<Services>> response) {
                if (response.isSuccessful()) {
                    List<Services> servicesList = response.body();

                    recyclerServiceAdapter.setData(servicesList);
                    serviceRecyclerView.setAdapter(recyclerServiceAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Services>> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }
}
