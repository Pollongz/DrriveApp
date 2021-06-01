package com.example.drrive.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.model.Damage;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public class RecyclerDamageAdapter extends RecyclerView.Adapter<RecyclerDamageAdapter.MyViewHolder> {

    private List<Damage> damagesList;
    private Context context;

    public void setData(List<Damage> damagesList) {
        this.damagesList = damagesList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerDamageAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_damage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerDamageAdapter.MyViewHolder holder, int position) {
        Damage damage = damagesList.get(position);

        String damageDescription = damage.getDescription();
        String damageDate = damage.getDate();
        String reportedBy = "Zgłoszono przez: " + damage.getUsersData().getFirstName() + " " +
                damage.getUsersData().getLastName();

        holder.damageDescriptionTv.setText(damageDescription);
        holder.damageDateTv.setText(damageDate);
        holder.reportedByTv.setText(reportedBy);
        holder.damagePhoto.setImageResource(R.drawable.photo3);
    }

    @Override
    public int getItemCount() {
        return damagesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView damageDescriptionTv;
        private final TextView damageDateTv;
        private final TextView reportedByTv;
        private final ImageView damagePhoto;

        public MyViewHolder(@NonNull @NotNull View view) {
            super(view);
            damageDescriptionTv = view.findViewById(R.id.damageDescriptionTv);
            damageDateTv = view.findViewById(R.id.damageDateTv);
            reportedByTv = view.findViewById(R.id.reportedByTv);
            damagePhoto = view.findViewById(R.id.damagePhoto);
        }
    }
}
