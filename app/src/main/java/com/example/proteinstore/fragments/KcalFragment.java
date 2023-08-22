package com.example.proteinstore.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proteinstore.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

//AdapterView.OnItemSelectedListener,
public class KcalFragment extends Fragment implements View.OnClickListener {
    Spinner Gender, Activity_Level;
    TextInputLayout Age, Height, Weight;
    MaterialCardView Result_Card;
    Button CalculateBtn;
    TextView WarningMessage, Result1, Result2,Result3;
    String text1, text2, text3;
    double weight = 0, height = 0, activityFactor = 0;
    int age = 0, bmr = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_kcal_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //initialization
        Gender = (Spinner) view.findViewById(R.id.gender);
        Activity_Level = (Spinner) view.findViewById(R.id.activity_level);
        Age = (TextInputLayout) view.findViewById(R.id.user_age);
        Height = (TextInputLayout) view.findViewById(R.id.user_height);
        Weight = (TextInputLayout) view.findViewById(R.id.user_weight);
        CalculateBtn = (Button) view.findViewById(R.id.calculateBtn);
        WarningMessage = (TextView) view.findViewById(R.id.warning_message);
        Result_Card = (MaterialCardView) view.findViewById(R.id.result_card);
        Result1 = (TextView) view.findViewById(R.id.result1);
        Result2 = (TextView) view.findViewById(R.id.result2);
        Result3=(TextView) view.findViewById(R.id.result3);



        //Listener

        //listener for gender
        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    bmr = 1;
                    System.out.println("");
                    //   Toast.makeText(getContext(),"item is "+position,Toast.LENGTH_LONG).show();
                } else if (position == 1) {
                    bmr = 2;
                    // bmr= (int) ((66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age))*activityFactor);
                } else if (position == 2) {
                    // bmr = (int) ((655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age))*activityFactor);
                    bmr = 3;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end of gender listener


        //listener for activity
        Activity_Level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        activityFactor = 0;
                        break;
                    case 1:
                        activityFactor = 1.2;
                        break;
                    case 2:
                        activityFactor = 1.375;
                        break;
                    case 3:
                        activityFactor = 1.55;
                        break;
                    case 4:
                        activityFactor = 1.725;
                        break;
                    case 5:
                        activityFactor = 1.9;
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        CalculateBtn.setOnClickListener(this);


    }//end of onViewCreated

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v == CalculateBtn) {
            if (bmr == 1) {
                WarningMessage.setVisibility(View.VISIBLE);
                // Toast.makeText(getContext(),"make sure your enter right",Toast.LENGTH_LONG).show();
            } else if (bmr == 2) {
                //retrieve data from Edit texts
                age = Integer.parseInt(Objects.requireNonNull(Age.getEditText()).getText().toString().trim());
                weight = Double.parseDouble(Objects.requireNonNull(Weight.getEditText()).getText().toString().trim());
                height = Double.parseDouble(Objects.requireNonNull(Height.getEditText()).getText().toString().trim());

                WarningMessage.setVisibility(View.GONE);
                Result_Card.setVisibility(View.VISIBLE);

                bmr = (int) ((66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age)) * activityFactor);
                text1 = "Your calorie needs per day is: " + bmr;
                text2="You should consume" +(bmr-500)+ "calories per day to lose weight.";
                text3="You should consume" +(bmr+500)+ "calories per day to get weight.";
                Result1.setText(text1);
                Result2.setText(text2);
                Result3.setText(text3);
            }else if (bmr == 3) {
                //retrieve data from Edit texts
                age = Integer.parseInt(Objects.requireNonNull(Age.getEditText()).getText().toString().trim());
                weight = Double.parseDouble(Objects.requireNonNull(Weight.getEditText()).getText().toString().trim());
                height = Double.parseDouble(Objects.requireNonNull(Height.getEditText()).getText().toString().trim());

                WarningMessage.setVisibility(View.GONE);
                Result_Card.setVisibility(View.VISIBLE);

                bmr = (int) ((655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age))*activityFactor);
                text1 = "Your calorie needs per day is: " + bmr;
                text2="You should consume" +(bmr-500)+ "calories per day to lose weight.";
                text3="You should consume" +(bmr+500)+ "calories per day to get weight.";
                Result1.setText(text1);
                Result2.setText(text2);
                Result3.setText(text3);


            }


        }

    }
}