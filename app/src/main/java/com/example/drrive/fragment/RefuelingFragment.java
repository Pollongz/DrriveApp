package com.example.drrive.fragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Car;
import com.example.drrive.model.Refueling;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class RefuelingFragment extends Fragment {

    private Spinner chooseCarSpinner;
    private Spinner chooseFuelType;
    private Integer companyId;
    private Integer carId;
    private TextView fuelDateCalendarTv;
    private EditText fuelQuantityEt;
    private EditText fuelCostEt;
    private EditText mileageEt;
    private Button addRefuelBtn;
    private ProgressBar progressBar;

    final Calendar c = Calendar.getInstance();
    private String choosenDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    public RefuelingFragment() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
            @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_add_refuel, container, false);

        chooseCarSpinner = view.findViewById(R.id.chooseCarSpinner);
        chooseFuelType = view.findViewById(R.id.chooseFuelType);
        fuelQuantityEt = view.findViewById(R.id.fuelQuantityEt);
        fuelCostEt = view.findViewById(R.id.fuelCostEt);
        mileageEt = view.findViewById(R.id.mileageEt);
        fuelDateCalendarTv = view.findViewById(R.id.fuelDateCalendarTv);
        addRefuelBtn = view.findViewById(R.id.addRefuelBtn);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        fuelDateCalendarTv.setText(getCurrentDate());
        fuelDateCalendarTv.setOnClickListener(v -> chooseDate(fuelDateCalendarTv));

        chooseFuelType.setAdapter(createSpinner());

        SharedPreferences preferences = getActivity().getSharedPreferences("logged", MODE_PRIVATE);
        companyId = preferences.getInt("companyId", 0);

        getAllCars(companyId);

        addRefuelBtn.setOnClickListener(v -> addRefueling());

        return view;
    }

    private SpinnerAdapter createSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.fuel_type, R.layout.spinner_item);
        return adapter;
    }

    public String getCurrentDate() {
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        if (mDay < 10 && mMonth < 10) {
            return "0" + mDay + ".0" + (mMonth + 1) + "." + mYear;
        } else if (mDay < 10) {
            return "0" + mDay + "." + (mMonth + 1) + "." + mYear;
        } else if (mMonth < 10) {
            return mDay + ".0" + (mMonth + 1) + "." + mYear;
        } else {
            return mDay + "." + (mMonth + 1) + "." + mYear;
        }
    }

    private void chooseDate(TextView option) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    if (dayOfMonth < 10 && monthOfYear < 9) {
                        choosenDate = "0" + dayOfMonth + ".0" + (monthOfYear + 1) + "." + year;
                    } else if (dayOfMonth < 10) {
                        choosenDate = "0" + dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                    } else if (monthOfYear < 9) {
                        choosenDate = dayOfMonth + ".0" + (monthOfYear + 1) + "." + year;
                    } else {
                        choosenDate = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                    }
                    option.setText(choosenDate);
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
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

    private void addRefueling() {
        Car car = (Car) chooseCarSpinner.getSelectedItem();
        carId = car.getIdCar();

        if (fuelQuantityEt.getText().toString().trim().length() == 0
                || fuelQuantityEt.getText().toString().trim().equals(".")
        ) {
            fuelQuantityEt.setError("To pole jest wymagane");
            fuelQuantityEt.requestFocus();
        } else if (fuelCostEt.getText().toString().trim().length() == 0
                || fuelCostEt.getText().toString().trim().equals(".")
        ) {
            fuelCostEt.setError("To pole jest wymagane");
            fuelCostEt.requestFocus();
        } else if (mileageEt.getText().toString().trim().length() == 0) {
            mileageEt.setError("To pole jest wymagane");
            mileageEt.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            Refueling refueling = new Refueling();
            refueling.setCarId(carId);
            refueling.setFuelType(chooseFuelType.getSelectedItem().toString().trim());
            refueling.setFuelQuantity(Float.parseFloat(fuelQuantityEt.getText().toString().trim()));
            refueling.setFuelCost(Float.parseFloat(fuelCostEt.getText().toString().trim()));
            refueling.setMileage(Integer.parseInt(mileageEt.getText().toString().trim()));
            refueling.setDate(fuelDateCalendarTv.getText().toString().trim());

            Call<Void> refuelingResponseCall = ApiClient.getUserService(getActivity()).addNewRefueling(refueling);
            refuelingResponseCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    if (response.isSuccessful()) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new com.example.drrive.fragment.SuccessFragment()).commit();
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getActivity(), "Wystąpił błąd: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
