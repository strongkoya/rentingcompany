package com.example.rentingcompany.Connection;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.rentingcompany.MainActivity;
import com.example.rentingcompany.Models.Property;

import java.util.List;


public class ConnectionAsyncTask extends AsyncTask<String, String,
        String> {
    Activity activity;

    public ConnectionAsyncTask(Activity activity) {

        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... params) {
        if (com.example.rentingcompany.Connection.HttpManager.getData(params[0]) != null) {
            String data = com.example.rentingcompany.Connection.HttpManager.getData(params[0]);
            return data;
        } else return "";
    }


}
