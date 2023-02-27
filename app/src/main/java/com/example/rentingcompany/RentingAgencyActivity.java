package com.example.rentingcompany;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rentingcompany.databinding.ActivityRentingAgencyBinding;
import com.google.android.material.navigation.NavigationView;

public class RentingAgencyActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityRentingAgencyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRentingAgencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       // setSupportActionBar(binding.appBarRentingAgency.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_add, R.id.nav_edit, R.id.nav_request, R.id.nav_renting_agency_history, R.id.nav_profile, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_renting_agency);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.renting_agency, menu);

        TextView username = (TextView) findViewById(R.id.nav_header_username);
        TextView email = (TextView) findViewById(R.id.nav_header_email);
        username.setText(MainActivity.username + " (Renting Agency)");
        email.setText(MainActivity.email);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.mipmap.profile_picture_round);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_renting_agency);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}