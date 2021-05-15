package com.example.drrive.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.DateFormat;
import com.example.drrive.R;
import com.example.drrive.activity.MainActivity;
import com.example.drrive.api.ApiClient;
import com.example.drrive.model.Post;
import com.example.drrive.model.User;
import com.example.drrive.model.UsersData;
import com.example.drrive.recyclerview.RecyclerNotificationAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment implements RecyclerNotificationAdapter.RecyclerViewClickListener {

    private TextView todaysDateTv;
    private String todaysDate;
    private TextView myFullNameTv;
    private TextView myCompanyTv;
    private RecyclerView recyclerView;
    private Integer companyId;
    private RecyclerNotificationAdapter recyclerNotificationAdapter;
    private RecyclerNotificationAdapter.RecyclerViewClickListener listener;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        todaysDateTv = view.findViewById(R.id.todaysDateTv);
        myFullNameTv = view.findViewById(R.id.myFullNameTv);
        myCompanyTv = view.findViewById(R.id.myCompanyTv);
        todaysDate = DateFormat.getCurrentDate();
        todaysDateTv.setText(todaysDate);

        recyclerView = view.findViewById(R.id.postRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerNotificationAdapter = new RecyclerNotificationAdapter(this::onClick);

        getCurrentUser();
        getAllPosts(companyId);

        return view;
    }

    public void getAllPosts(Integer idCompany) {
        Call<List<Post>> call = ApiClient.getUserService(getContext()).getCompanyPosts(idCompany);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> postsList = response.body();

                    recyclerNotificationAdapter.setData(postsList);
                    recyclerView.setAdapter(recyclerNotificationAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }

    // getting username from currently logged user
    public void getCurrentUser() {
        Call<String> call = ApiClient.getUserService(this.getActivity()).getCurrentUser();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String user = response.body();
                    getUserByUsername(user);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }

    // getting user object from username returned from currently logged user
    public void getUserByUsername(String user) {
        Call<User> callUser = ApiClient.getUserService(this.getActivity()).getUserByUsername(user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User thisUser = response.body();
                    Integer userId = thisUser.getIdUsers();// get User object nad then id of this user
                    getUsersDataByUserId(userId); // get UsersData object
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // TODO something went wrong
            }
        });
    }

    // getting UsersData object from userId
    public void getUsersDataByUserId(Integer user) {
        Call<UsersData> callUsersData = ApiClient.getUserService(this.getActivity()).getUserDataByUserId(user);
        callUsersData.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                if (response.isSuccessful()) {

                    UsersData thisUserData = response.body();

                    String userFullName = thisUserData.getFirstName()
                            + " " + thisUserData.getLastName();
                    String companyInfo = thisUserData.getCompany()
                            .getName() + ", " + thisUserData.getCompany()
                            .getAddress()
                            .getCity();

                    companyId = thisUserData.getCompany().
                            getIdCompany();

                    getAllPosts(companyId);
                    myFullNameTv.setText(userFullName);
                    myCompanyTv.setText(companyInfo);
                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                myFullNameTv.setText(t.getMessage());
                myCompanyTv.setText(t.getMessage());
            }
        });
    }

    @Override
    public void onClick(Post post) {
        Toast.makeText(getContext(), "clicked on post " + post.toString(), Toast.LENGTH_SHORT).show();
    }
}
