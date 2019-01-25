package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity{

    BottomNavigationView bottomNav;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new HomeFragment()).commit();
        firebaseAuth = FirebaseAuth.getInstance();
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();

                        break;
                    case R.id.nav_favourites:
                        selectedFragment = new FavouriteFragment();
                        break;
                    case R.id.nav_search:
                        selectedFragment = new SearchFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, selectedFragment).addToBackStack(null).commit();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_spinner_user,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.Sign_out:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        return true;
    }
}