package com.example.rentingcompany;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentingcompany.Connection.ConnectionAsyncTask;
import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.Models.Property;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String username = "";
    public static String email = "";
    public static String accountStatus = "";
    Button connectButton;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    int connectionFlag = 1;

    public static boolean validateJavaDate(String strDate) {
        /* Check if date is 'null' */
        if (strDate.trim().equals("")) {
            return true;
        }
        /* Date is not 'null' */
        else {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try {
                Date javaDate = sdfrmt.parse(strDate);
            }
            /* Date format is invalid */ catch (ParseException e) {
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, "EXP4", null, 1);

        /*
         *   DELETE PROPERTY TABLE
         */
//        dataBaseHelper.deleteAllProperties();
//        dataBaseHelper.deleteAllHaves();
//        dataBaseHelper.deleteAllContracts();
//        dataBaseHelper.deleteAllRequests();

        //Initial values (email & password)

        //dataBaseHelper.insertTenant(new Tenant("a", "Abdulghaffar", "Abed", "Male", "t", SHA.encryptSHA512("abd"), "Palestinian", "1500", "Palestine", "5", "Palestine", "Jerusalem", "00970598321784"));
        //dataBaseHelper.insertRentingAgency(new RentingAgency("t", "Tareq Shannak", "t", SHA.encryptSHA512("tareq"), "Palestine", "Jerusalem", "00970598261423"));


        setContentView(R.layout.activity_main);
        setProgress(false);
        connectButton = (Button) findViewById(R.id.connectButton);


        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int connectionFlag = 0;
                connectivityManager = (ConnectivityManager) getSystemService((Context.CONNECTIVITY_SERVICE));
                networkInfo = connectivityManager.getActiveNetworkInfo();

                if ((networkInfo != null) && (networkInfo.isConnected())) {
                    connectionFlag = 1;
                }


                if (connectionFlag == 1) {


                    ConnectionAsyncTask connectionAsyncTask = new
                            ConnectionAsyncTask(MainActivity.this);

                    connectionAsyncTask.execute("https://run.mocky.io/v3/3ebd3635-7c27-4576-9d82-daed37390f2e");

                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);

                    MainActivity.this.startActivity(intent);
                    finish();


                } else {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "No Internet Connection", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });


    }

    public void setButtonText(String text) {
        connectButton.setText(text);
    }

    public void setProgress(boolean progress) {
        ProgressBar progressBar = findViewById(R.id.progressBar);

        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
        } else {
            System.out.println("\n\n\nEEmpty\n\n\n");
            progressBar.setVisibility(View.GONE);
        }
    }

    public void fillProperties(List<Property> Properties) {

        if (Properties != null) {

        } else {
            Toast toast = Toast.makeText(MainActivity.this,
                    "Connection Error", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void fillPropertiesIntoDB(List<Property> Properties) {
        DataBaseHelper dataBaseHelper;
        dataBaseHelper = new DataBaseHelper(MainActivity.this, "EXP4", null, 1);

        if (Properties != null) {
            for (Property p : Properties) {
                System.out.println("\n\n\n****************^^^^^^^^^^^^^^^^^^^^^^^^^^^%%%%%%%%%%%%%%%%%%%%%%%%$$$$$$$$$$$$$$$$$$$$$$$$$$$######################@@@@@@@@@\n\n\n");
                System.out.println(p);
               if(p!=null){ dataBaseHelper.insertProperty(p);}
        }} else {

            Toast toast = Toast.makeText(MainActivity.this,
                    "Inserting Error", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}