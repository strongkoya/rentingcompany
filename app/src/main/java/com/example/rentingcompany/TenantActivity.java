package com.example.rentingcompany;

import static com.example.rentingcompany.MainActivity.accountStatus;

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

import com.example.rentingcompany.databinding.ActivityTenantBinding;
import com.google.android.material.navigation.NavigationView;

public class TenantActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityTenantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTenantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       //setSupportActionBar(binding.appBarTenant.toolbar);
       setSupportActionBar(findViewById(R.id.toolbar));
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_search, R.id.nav_menu, R.id.nav_tenant_history, R.id.nav_profile, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_tenant);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.tenant, menu);
        TextView username = (TextView) findViewById(R.id.nav_header_username);
        TextView email = (TextView) findViewById(R.id.nav_header_email);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        if (accountStatus.equalsIgnoreCase("Tenant")) {
            username.setText(MainActivity.username + " (Tenant)");
            image.setImageResource(R.mipmap.profile_picture_round);
        } else {
            username.setText(MainActivity.username);
            image.setImageResource(R.mipmap.guest_picture_round);
        }

        email.setText(MainActivity.email);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_tenant);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}