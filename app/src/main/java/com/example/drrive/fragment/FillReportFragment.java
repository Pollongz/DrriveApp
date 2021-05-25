package com.example.drrive.fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Car;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class FillReportFragment extends Fragment {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    public Uri image_uri;
    private Spinner chooseCarSpinner;
    private Integer companyId;
    private EditText mileageEt;
    private RadioButton notDamagedRb;
    private RadioButton damagedRb;
    private ImageView newDamagePhoto;
    private Button addPhotoBtn;
    private Button sendReportBtn;
    private Integer carId;
    private ProgressBar progressBar;

    public FillReportFragment() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
            @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_fill_report, container, false);

        chooseCarSpinner = view.findViewById(R.id.chooseCarSpinner);
        mileageEt = view.findViewById(R.id.mileageEt);
        notDamagedRb = view.findViewById(R.id.notDamagedRb);
        damagedRb = view.findViewById(R.id.damagedRb);
        newDamagePhoto = view.findViewById(R.id.newDamagePhoto);
        addPhotoBtn = view.findViewById(R.id.addPhotoBtn);
        sendReportBtn = view.findViewById(R.id.sendReportBtn);

        newDamagePhoto.setVisibility(View.GONE);
        addPhotoBtn.setVisibility(View.GONE);

        SharedPreferences preferences = getActivity().getSharedPreferences("logged", MODE_PRIVATE);
        companyId = preferences.getInt("companyId", 0);

        getAllCars(companyId);

        damagedRb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            newDamagePhoto.setVisibility(View.GONE);
            addPhotoBtn.setVisibility(View.GONE);
        });

        notDamagedRb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            newDamagePhoto.setVisibility(View.VISIBLE);
            addPhotoBtn.setVisibility(View.VISIBLE);
        });

        addPhotoBtn.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED ||
                        getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED) {
                    String [] permission = {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    };
                    requestPermissions(permission, PERMISSION_CODE);
                } else {
                    openCamera();
                }
            }
        });

        return view;
    }

    private void openCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");
        image_uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            newDamagePhoto.setImageURI(image_uri);
        }
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
}
