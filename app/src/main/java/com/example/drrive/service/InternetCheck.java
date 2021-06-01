package com.example.drrive.service;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;
import android.widget.Button;

import com.example.drrive.R;

public class InternetCheck {

    Context context;

    public InternetCheck(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void connectionDialog(Context context) {
        Dialog pDialog = new Dialog(context);
        pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pDialog.setCancelable(false);
        pDialog.setContentView(R.layout.dialog_connection);

        final Button exitDialog = pDialog.findViewById(R.id.exitDialog);

        exitDialog.setOnClickListener(v -> ((Activity)context).finish());

        pDialog.show();
    }
}
