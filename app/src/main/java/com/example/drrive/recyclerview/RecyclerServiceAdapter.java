package com.example.drrive.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.model.Services;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerServiceAdapter extends RecyclerView.Adapter<RecyclerServiceAdapter.MyViewHolder> {

    private List<Services> servicesList;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Services> servicesList) {
        this.servicesList = servicesList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_service, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerServiceAdapter.MyViewHolder holder, int position) {

        Services service = servicesList.get(position);

        String serviceDescription = service.getDescription();
        String date = service.getDate();

        String serviceDate = date.substring(0,10);

        holder.serviceDescriptionTv.setText(serviceDescription);
        holder.serviceDateTv.setText(serviceDate);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView serviceDescriptionTv;
        private final TextView serviceDateTv;

        public MyViewHolder(@NonNull @NotNull View view) {
            super(view);
            serviceDescriptionTv = view.findViewById(R.id.serviceDescriptionTv);
            serviceDateTv = view.findViewById(R.id.serviceDateTv);
        }
    }
}