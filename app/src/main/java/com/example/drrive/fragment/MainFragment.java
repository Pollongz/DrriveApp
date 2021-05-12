package com.example.drrive.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drrive.DateFormat;
import com.example.drrive.R;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends Fragment {

    private TextView todayDateView;
    private String todaysDate;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        todayDateView = view.findViewById(R.id.todaysDateTv);
        todaysDate = DateFormat.getCurrentDate();
        todayDateView.setText(todaysDate);

        return view;
    }
}
