package com.example.proteinstore.inHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.proteinstore.R;
import com.google.android.material.card.MaterialCardView;

public class Categoreis extends AppCompatActivity implements View.OnClickListener {
    private MaterialCardView BurnFat,HealthySnack,WeightGainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoreis);
        //initialization
        BurnFat =(MaterialCardView) findViewById(R.id.burnfat);
        WeightGainer =(MaterialCardView) findViewById(R.id.weightgainer);
        HealthySnack=(MaterialCardView) findViewById(R.id.healthysnack);

        //setOnCLickListener
        BurnFat.setOnClickListener(this);
        HealthySnack.setOnClickListener(this);
        WeightGainer.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v == BurnFat || v==HealthySnack||v==WeightGainer){
            startActivity(new Intent(getApplicationContext(), BurnFatProduct.class));
        }

    }
}