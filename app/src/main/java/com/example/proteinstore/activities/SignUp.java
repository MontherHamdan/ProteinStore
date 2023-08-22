package com.example.proteinstore.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proteinstore.R;
import com.example.proteinstore.apis.RetrofitSignUp;
import com.example.proteinstore.model.Result;
import com.google.android.material.textfield.TextInputLayout;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    public TextInputLayout name,password,email,phone;
    public Button RegBtn,CancelBtn;
    public String Name,Pass,Email,Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        name = (TextInputLayout) findViewById(R.id.userSignUp);
        password = (TextInputLayout) findViewById(R.id.passwordSignUp);
        email = (TextInputLayout) findViewById(R.id.emailSignUp);
        phone = (TextInputLayout) findViewById(R.id.phoneSignUp);

        RegBtn = (Button) findViewById(R.id.signupBtn);
        CancelBtn=(Button)findViewById(R.id.cancelBtn);

        RegBtn.setOnClickListener(this);
        CancelBtn.setOnClickListener(this);

    }//End onCreate method

    private void insertUser(){

        //retrieve data from Edit texts
        Name = name.getEditText().getText().toString().trim();
        Pass = password.getEditText().getText().toString().trim();
        Email = email.getEditText().getText().toString().trim();
        Phone = phone.getEditText().getText().toString().trim();

        //Here we will handle the http request to insert user to mysql db


        Call<Result> call = RetrofitSignUp.getInstance().getMyApi().insertUser(Name,Pass,Email,Phone);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {


                if(response.body().getError() == true){

                    Log.d("something goes wrong --- > ", response.body().getMessage());
                    Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }else if(response.body().getError()==false){

                    Log.d("Response ---> ","User registered successfully");

                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(),SignIn.class));


                }




            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Log.d("Failed to Insert Data ---> ",t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to Insert Data --> "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }//End insertUser method
    @Override
    public void onClick(View view) {
        if (view == RegBtn) {
            if (!UsernameValidate()||!EmailValidate()|| !PasswordValidate()|| !PhoneValidate())
                return ;
            else{insertUser();}

        }else if(view == CancelBtn){

            startActivity(new Intent(getApplicationContext(),SignIn.class));
        }
    }//End of onClick





    //username
    public boolean UsernameValidate(){
        Name=name.getEditText().getText().toString().trim();
        if (Name.isEmpty()){
            name.setError("this field should not be empty");
            return false;
        }else{
            name.setErrorEnabled(false);
            return true;

        }
    }


    //password

    public boolean PasswordValidate(){
        Pass=password.getEditText().getText().toString().trim();

        if (Pass.isEmpty()){
            password.setError("this filed should not be empty");
            return false;
        }
        else if (Pass.length()<8){
            password.setError("password should be 8 digit ");
            return false;
        }
        else {
            password.setErrorEnabled(false);
            return true;
        }
    }

    //email

    public boolean EmailValidate(){
        Email=email.getEditText().getText().toString().trim();

        if (Email.isEmpty()){
            email.setError("this filed should not be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("your email is not correct");
            return false;
        }
        else {
            email.setErrorEnabled(false);
            return true;
        }
    }

    //phone

    public boolean PhoneValidate(){
        Phone=phone.getEditText().getText().toString().trim();

        if (Phone.isEmpty()){
            phone.setError("this filed should not be empty");
            return false;
        }
        else if (Phone.length() != 9){
            phone.setError("your number should be 9 number");
            return false;
        }
        else {
            phone.setErrorEnabled(false);
            return true;
        }
    }









}
