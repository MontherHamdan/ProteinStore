package com.example.proteinstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proteinstore.R;
import com.example.proteinstore.ShsredPref.SharedPrefManager;
import com.example.proteinstore.apis.RetrofitSignIn;
import com.example.proteinstore.fragments.HomeFragment;
import com.example.proteinstore.fragments.SettingFragment;
import com.example.proteinstore.model.Result;
import com.example.proteinstore.model.User;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class SignIn extends AppCompatActivity implements View.OnClickListener {


    private TextInputLayout EmailTextInputLayout, PasswordTextInputLayout;
    private Button buttonSignIn,signUp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);


        EmailTextInputLayout = (TextInputLayout) findViewById(R.id.TextInputEmailLayout);
        PasswordTextInputLayout = (TextInputLayout) findViewById(R.id.TextInputPassword);

        buttonSignIn = (Button) findViewById(R.id.signInBtn);
        signUp = (Button) findViewById(R.id.signUpActivity);


        buttonSignIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void userSignIn() {

        String email = EmailTextInputLayout.getEditText().getText().toString().trim();
        String password = PasswordTextInputLayout.getEditText().getText().toString().trim();

        Call<Result> call = RetrofitSignIn.getInstance().getMyApi().userLogin(email,password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (!response.body().getError()) {
                    Intent intent = new Intent (SignIn.this, MainActivity.class);

                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Welcome "+response.body().getUser().getName(),Toast.LENGTH_LONG).show();


                    User user = new User((int)response.body().getUser().getId(),response.body().getUser().getName(),response.body().getUser().getEmail(),response.body().getUser().getPassword(),(String)response.body().getUser().getPhone());

                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {


                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit ERROR -->",t.getMessage());

            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSignIn) {
            userSignIn();
        }else if(view == signUp){

            startActivity(new Intent(getApplicationContext(),SignUp.class));
        }
    }






}
