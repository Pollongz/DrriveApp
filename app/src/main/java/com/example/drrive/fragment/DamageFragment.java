package com.example.drrive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Damage;
import com.example.drrive.model.Post;
import com.example.drrive.recyclerview.RecyclerDamageAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DamageFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerDamageAdapter recyclerDamageAdapter;
    private Integer carId;

    public DamageFragment() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
            @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_car_history_damages, container, false);

        recyclerView = view.findViewById(R.id.damageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerDamageAdapter = new RecyclerDamageAdapter();

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            carId = bundle.getInt("carId" , -1);
            getAllDamages(carId);
        }

        return view;
    }

    public void getAllDamages(Integer idCar) {
        Call<List<Damage>> call = ApiClient.getUserService(getContext()).getCarsDamages(idCar);
        call.enqueue(new Callback<List<Damage>>() {
            @Override
            public void onResponse(Call<List<Damage>> call, Response<List<Damage>> response) {
                if (response.isSuccessful()) {
                    List<Damage> damagesList = response.body();

                    recyclerDamageAdapter.setData(damagesList);
                    recyclerView.setAdapter(recyclerDamageAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Damage>> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }
}
