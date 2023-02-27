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
    protected void onPreExecute() {

        ((MainActivity) activity).setButtonText("Connecting");
        super.onPreExecute();
        ((MainActivity) activity).setProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {
        if (com.example.rentingcompany.Connection.HttpManager.getData(params[0]) != null) {
            String data = com.example.rentingcompany.Connection.HttpManager.getData(params[0]);
            return data;
        } else return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ((MainActivity) activity).setProgress(false);
        List<Property> Properties = com.example.rentingcompany.Connection.PropertyJasonParser.getObjectFromJason(s);
        ((MainActivity) activity).fillProperties(Properties);
        ((MainActivity) activity).fillPropertiesIntoDB(Properties);


    }
}
