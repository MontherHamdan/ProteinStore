package com.example.proteinstore.inSetting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proteinstore.R;
import com.example.proteinstore.ShsredPref.SharedPrefManager;
import com.example.proteinstore.apis.RetrofitDelete;
import com.example.proteinstore.model.Result;
import com.example.proteinstore.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAccount extends AppCompatActivity {
    Button deleteBtn;
    int Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        //Get the Id of current user to delete the account
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        Id = user.getId();

        deleteBtn = findViewById(R.id.deleteBtn);
       // deleteBtn.setBackgroundColor(Color.RED);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteUser();
            }
        });


    }//end of onCreate

    public void DeleteUser(){

        Call<Result> call = RetrofitDelete.getInstance().getMyApi().deleteUser(Id);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if(response.body().getError() == false){

                    Toast.makeText(getApplicationContext(),"Response msg ---> "+ response.body().getMessage(),Toast.LENGTH_LONG).show();
                    finish();
                    Log.d("msg ---> ",response.body().getMessage());
                    SharedPrefManager.getInstance(getApplicationContext()).Logout();
                }else if (response.body().getError() == true){
                    Toast.makeText(getApplicationContext(),"Response msg ---> "+ response.body().getMessage(),Toast.LENGTH_LONG).show();
                    Log.d("msg ---> ",response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Error ---> "+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error ---> ",t.getMessage());

            }
        });
    }
}



