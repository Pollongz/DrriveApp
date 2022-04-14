package com.example.drrive.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.fragment.MarkServiceAsDoneFragment;
import com.example.drrive.fragment.PlannedServicesFragment;
import com.example.drrive.model.PlannedServices;
import com.example.drrive.model.Services;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;


public class RecyclerPlannedServicesAdapter extends RecyclerView.Adapter<RecyclerPlannedServicesAdapter.MyViewHolder> {

    private static final String SERVICE_DESCRIPTION = "description";
    public static final String SERVICE_ID = "service_id";
    public static final String CAR_ID = "car_id";

    private List<PlannedServices> plannedServicesList;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<PlannedServices> plannedServicesList) {
        this.plannedServicesList = plannedServicesList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerPlannedServicesAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_planned, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerPlannedServicesAdapter.MyViewHolder holder, int position) {

        PlannedServices plannedService = plannedServicesList.get(position);

        String serviceDescription = plannedService.getDescription();
        String serviceDate = plannedService.getDate();
        int serviceId = plannedService.getIdPlannedService();
        int carId = plannedService.getCar().getIdCar();

        String date = serviceDate.substring(0, 10);
        String hour = serviceDate.substring(11);

        String fullDate = date + " \n" + hour;

        holder.plannedDescriptionTv.setText(serviceDescription);
        holder.plannedDateTv.setText(fullDate);
        holder.doneBtn.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment markServiceAsDoneFragment = new MarkServiceAsDoneFragment();

            Bundle bundle = new Bundle();
            bundle.putInt(SERVICE_ID, serviceId);
            bundle.putInt(CAR_ID, carId);
            bundle.putString(SERVICE_DESCRIPTION, serviceDescription);
            markServiceAsDoneFragment.setArguments(bundle);

            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    markServiceAsDoneFragment).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return plannedServicesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView plannedDescriptionTv;
        private final TextView plannedDateTv;
        private final Button doneBtn;

        public MyViewHolder(@NonNull @NotNull View view) {
            super(view);
            plannedDescriptionTv = view.findViewById(R.id.plannedDescriptionTv);
            plannedDateTv = view.findViewById(R.id.plannedDateTv);
            doneBtn = view.findViewById(R.id.doneBtn);
        }
    }
}
