package com.example.drrive.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.model.PlannedServices;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecyclerPlannedServicesAdapter extends RecyclerView.Adapter<RecyclerPlannedServicesAdapter.MyViewHolder> {

    private List<PlannedServices> plannedServicesList;
    private Context context;

    public void setData(List<PlannedServices> plannedServicesList) {
        this.plannedServicesList = plannedServicesList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerPlannedServicesAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RecyclerPlannedServicesAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_planned, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerPlannedServicesAdapter.MyViewHolder holder, int position) {

        PlannedServices plannedService = plannedServicesList.get(position);

        String serviceDescription = plannedService.getDescription();
        String serviceDate = plannedService.getDate();

        String date = serviceDate.substring(0, 10);
        String hour = serviceDate.substring(11);

        String fullDate = date + " \n" + hour;

        holder.plannedDescriptionTv.setText(serviceDescription);
        holder.plannedDateTv.setText(fullDate);
    }

    @Override
    public int getItemCount() {
        return plannedServicesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView plannedDescriptionTv;
        private final TextView plannedDateTv;

        public MyViewHolder(@NonNull @NotNull View view) {
            super(view);
            plannedDescriptionTv = view.findViewById(R.id.plannedDescriptionTv);
            plannedDateTv = view.findViewById(R.id.plannedDateTv);
        }
    }
}
