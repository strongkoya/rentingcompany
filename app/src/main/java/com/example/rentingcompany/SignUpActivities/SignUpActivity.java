package com.example.rentingcompany.SignUpActivities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentingcompany.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button rentingAgencyButton = (Button) findViewById(R.id.rentingAgencyButton);
        Button tenantButton = (Button) findViewById(R.id.tenantButton);

        rentingAgencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUpActivity.this, SignUpRentingAgencyActivity.class);
                SignUpActivity.this.startActivity(intent);
                finish();
            }

        });

        tenantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUpActivity.this, SignUpTenantActivity.class);
                SignUpActivity.this.startActivity(intent);
                finish();
            }

        });

    }
}