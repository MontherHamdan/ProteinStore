package com.example.proteinstore.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proteinstore.R;
import com.example.proteinstore.inHome.Categoreis;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private MaterialCardView FirstNutrition,Proteink,KabsFit,DNutrition,KarradaGroup,OrangeNutrition;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //initialization
        FirstNutrition =(MaterialCardView) view.findViewById(R.id.firstnutritionCard);
        Proteink=(MaterialCardView) view.findViewById(R.id.protinekCard);
        KabsFit=(MaterialCardView) view.findViewById(R.id.kabsFitCard);
        DNutrition=(MaterialCardView) view.findViewById(R.id.doctorNutritionCard);
        KarradaGroup=(MaterialCardView) view.findViewById(R.id.karradaCard);
        OrangeNutrition=(MaterialCardView) view.findViewById(R.id.orangeSupllementCard);

        //setOnCLickListener
        FirstNutrition.setOnClickListener(this);
        Proteink.setOnClickListener(this);
        KabsFit.setOnClickListener(this);
        DNutrition.setOnClickListener(this);
        KarradaGroup.setOnClickListener(this);
        OrangeNutrition.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v == FirstNutrition || v==Proteink || v==KabsFit || v==DNutrition || v==KarradaGroup || v==OrangeNutrition){
            startActivity(new Intent(getContext(), Categoreis.class));
        }
    }
}