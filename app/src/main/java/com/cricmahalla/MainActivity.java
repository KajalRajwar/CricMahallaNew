package com.cricmahalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseInstallation;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    public void logIn(View view){
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For Test the connection
        ParseInstallation.getCurrentInstallation().saveInBackground();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);


        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_addTournament:
                Toast.makeText(this, "Add Tournament", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_myTournament:
                Toast.makeText(this, "My Tournament", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_royalTournament:
                Toast.makeText(this, "Royal Tournament", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_buildYourTeam:
                Toast.makeText(this, "Build Your Team", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_bookPlayer:
                Toast.makeText(this, "Book A Player", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_uploadPhoto:
                Toast.makeText(this, "Upload Photo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_shareApp:
                Toast.makeText(this, "Share The App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_rateApp:
                Toast.makeText(this, "Rate The App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}