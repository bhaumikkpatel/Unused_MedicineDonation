package com.example.medonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medonation.databinding.ActivityAdminhomeBinding;
import com.example.medonation.databinding.ActivityDonorhomeBinding;
import com.google.android.material.navigation.NavigationView;

public class ADMINHOME extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDonorhomeBinding binding;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_admhm);

        Toolbar toolbar = findViewById(R.id.admintoolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.admin_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.adminnav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.adminprofile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_adminhome, new adminprofile()).commit();
                        break;
                    case R.id.showdonations:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_adminhome, new donations()).commit();
                        break;
                    case R.id.medicineshortageneed:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_adminhome, new medshortage_admin()).commit();
                        break;
                    case R.id.disasteralert_admin:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_adminhome,new disasteralert_admin()).commit();
                        break;
                    case R.id.ad_help_complaints:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_adminhome,new ad_help_complaints()).commit();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}
