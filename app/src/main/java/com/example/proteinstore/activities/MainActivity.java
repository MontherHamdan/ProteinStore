package com.example.proteinstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.proteinstore.R;
import com.example.proteinstore.ShsredPref.SharedPrefManager;
import com.example.proteinstore.fragments.FavouriteFragment;
import com.example.proteinstore.fragments.HomeFragment;
import com.example.proteinstore.fragments.KcalFragment;
import com.example.proteinstore.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentTransaction mFragmentTransaction;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ensure that user signed in
        if (!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(),SignIn.class));
        }

        //initialization
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNav);
        floatingActionButton=(FloatingActionButton) findViewById(R.id.floatingActionBtn);

        //call fragment
        mFragmentTransaction=getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.container,new HomeFragment());
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();


        //Listener for Cart Shopping Button
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CartShoppingActivity.class));
            }
        });


        //listener for bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment= null;
                switch (item.getItemId()){
                    case R.id.home:selectedFragment=new HomeFragment();
                        break;
                    case R.id.calories:selectedFragment=new KcalFragment();
                        break;
                   /* case R.id.favourite:selectedFragment=new FavouriteFragment();
                        break;*/
                    case R.id.setting:selectedFragment=new SettingFragment();
                        break;

                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment).commit();
                return true;
            }
        });

    }//end of onCreate


}