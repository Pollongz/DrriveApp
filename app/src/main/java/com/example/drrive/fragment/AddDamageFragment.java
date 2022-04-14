package com.example.drrive.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drrive.R;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Car;
import com.example.drrive.model.Damage;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDamageFragment extends Fragment {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    public Uri image_uri;
    private Spinner chooseCarSpinner;
    private Integer companyId;
    private Integer userDataId;
    private TextView policyNumber;
    private TextView assistanceNumber;
    private EditText mileageEt;
    private EditText addDescriptionEt;
    private ImageView newDamagePhoto;
    private Button addPhotoBtn;
    private Button sendReportBtn;
    private Integer carId;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(
            @NonNull @NotNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_add_damage, container, false);

        chooseCarSpinner = view.findViewById(R.id.chooseCarSpinner);
        policyNumber = view.findViewById(R.id.thisPolicyNumberTv);
        assistanceNumber = view.findViewById(R.id.thisAssistanceNumberTv);
        mileageEt = view.findViewById(R.id.mileageEt);
        addDescriptionEt = view.findViewById(R.id.addDescriptionEt);
        newDamagePhoto = view.findViewById(R.id.newDamagePhoto);
        addPhotoBtn = view.findViewById(R.id.addPhotoBtn);
        sendReportBtn = view.findViewById(R.id.sendReportBtn);
        progressBar = view.findViewById(R.id.progressBar);

        newDamagePhoto.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        SharedPreferences preferences = requireActivity().getSharedPreferences("logged", MODE_PRIVATE);
        companyId = preferences.getInt("companyId", 0);
        userDataId = preferences.getInt("userDataId", 0);

        getAllCars(companyId);

        addPhotoBtn.setOnClickListener(v -> {
            if (requireActivity().checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED ||
                    requireActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                String[] permission = {
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                requestPermissions(permission, PERMISSION_CODE);
            } else {
                openCamera();
            }
        });

        chooseCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setInsuranceData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        assistanceNumber.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", assistanceNumber.getText().toString().trim(), null));
            startActivity(intent);
        });

        sendReportBtn.setOnClickListener(v -> addDamage());

        return view;
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");
        image_uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            newDamagePhoto.setImageURI(image_uri);
            newDamagePhoto.setVisibility(View.VISIBLE);
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
                Toast.makeText(getActivity(), "Wystąpił błąd: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setInsuranceData() {
        Car car = (Car) chooseCarSpinner.getSelectedItem();
        String policyNum = car.getInsurance().getPolicyNumber();
        String assistanceNum = car.getInsurance().getAssistanceNumber();

        policyNumber.setText(policyNum);
        assistanceNumber.setText(assistanceNum);
    }

    private void addDamage() {
        Car car = (Car) chooseCarSpinner.getSelectedItem();
        carId = car.getIdCar();

        if (mileageEt.getText().toString().trim().length() == 0) {
            mileageEt.setError("To pole jest wymagane");
            mileageEt.requestFocus();
        } else if (addDescriptionEt.getText().toString().trim().length() == 0) {
            addDescriptionEt.setError("To pole jest wymagane");
            addDescriptionEt.requestFocus();
        } else {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedDate = localDate.format(formatter);

            Damage damage = new Damage();
            damage.setDescription(addDescriptionEt.getText().toString().trim());
            damage.setDate(formattedDate);
            damage.setCarId(carId);
            damage.setReportedById(userDataId);

            Call<Void> damageResponseCall = ApiClient.getUserService(getActivity()).addNewDamage(damage);
            damageResponseCall.enqueue(new Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    if (response.isSuccessful()) {
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
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
