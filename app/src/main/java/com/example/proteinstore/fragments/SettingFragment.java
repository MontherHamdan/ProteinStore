package com.example.proteinstore.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proteinstore.R;
import com.example.proteinstore.ShsredPref.SharedPrefManager;
import com.example.proteinstore.inSetting.AcountInfoActivity;
import com.example.proteinstore.inSetting.DeleteAccount;
import com.example.proteinstore.inSetting.PrivacyAndPolicy;
import com.example.proteinstore.inSetting.ReferAndEarn;
import com.google.android.material.card.MaterialCardView;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private MaterialCardView AcoountInfo,LogOut,DeleteCard,ReafferAndEarn,PrivacyAndPolicy;
    public static TextView navUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_setting_fragment,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //initialization
        AcoountInfo =(MaterialCardView) view.findViewById(R.id.accountInfo);
        LogOut =(MaterialCardView) view.findViewById(R.id.logoutbtn);
        DeleteCard =(MaterialCardView) view.findViewById(R.id.deleteCard);
        ReafferAndEarn=(MaterialCardView)view.findViewById(R.id.reffer_and_earn);
        PrivacyAndPolicy=(MaterialCardView)view.findViewById(R.id.privacy_policy);


        navUsername = (TextView)view.findViewById(R.id.user_name_nav);





        setHeaderInfo();


        //setOnClickListener
        AcoountInfo.setOnClickListener(this);
        LogOut.setOnClickListener(this);
        DeleteCard.setOnClickListener(this);
        ReafferAndEarn.setOnClickListener(this);
        PrivacyAndPolicy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==LogOut){
            getActivity().finish();
            SharedPrefManager.getInstance(getContext()).Logout();
        }else if(v==AcoountInfo){
            startActivity(new Intent(getContext(), AcountInfoActivity.class));
        }else if(v==DeleteCard){
            startActivity(new Intent(getContext(), DeleteAccount.class));
        }else if (v==ReafferAndEarn){
            startActivity(new Intent(getContext(), ReferAndEarn.class));
        }else if (v==PrivacyAndPolicy){
            startActivity(new Intent(getContext(), PrivacyAndPolicy.class));

        }
    }

    public void setHeaderInfo() {
        String name= SharedPrefManager.getInstance(getContext()).getUser().getName();
        String email=SharedPrefManager.getInstance(getContext()).getUser().getEmail();
        String phone=SharedPrefManager.getInstance(getContext()).getUser().getPhone();

        navUsername.setText(name);
        /*navEmail.setText(email);
        navPhone.setText(phone);*/
    }
}