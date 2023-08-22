package com.example.proteinstore.inSetting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proteinstore.R;
import com.example.proteinstore.activities.MainActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proteinstore.R;
import com.example.proteinstore.apis.RetrofitSignIn;
import com.example.proteinstore.apis.RetrofitSignUp;
import com.example.proteinstore.apis.RetrofitUpdate;
import com.example.proteinstore.fragments.SettingFragment;
import com.example.proteinstore.model.Result;
import com.example.proteinstore.model.User;
import com.example.proteinstore.ShsredPref.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcountInfoActivity extends AppCompatActivity {
    TextInputLayout name,password,email,phone;
    Button updateBtn;
    //public String Name,Pass,Email,Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_info);
        name = (TextInputLayout)findViewById(R.id.TextInputUsername);
        email = findViewById(R.id.TextInputEmail);
        password = findViewById(R.id.TextInputPassword);
        phone = findViewById(R.id.TextInputPhone);


        //get the Info of current user
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        name.getEditText().setText(user.getName());
        password.getEditText().setText(user.getPassword());
        email.getEditText().setText(user.getEmail());
        phone.getEditText().setText(user.getPhone());

        updateBtn = findViewById(R.id.updateBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  if (!UsernameValidate() || !EmailValidate() || !PasswordValidate() || !PhoneValidate()) {
                    return;
                } else {*/


                    Call<Result> call = RetrofitUpdate.getInstance().getMyApi().updateUser(user.getId(), name.getEditText().getText().toString(), email.getEditText().getText().toString(), password.getEditText().getText().toString(), phone.getEditText().getText().toString());
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {

                            if (response.body().getError() == true || response.body().getUser().getName() == null) {
                                Toast.makeText(getApplicationContext(), "response msg ---> " + response.body().getMessage(), Toast.LENGTH_LONG).show();

                            } else {


                                User user = new User(response.body().getUser().getId(), response.body().getUser().getName(), response.body().getUser().getEmail(), response.body().getUser().getPassword(), response.body().getUser().getPhone());
                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userUpdate(user);
                                //reload the info of navigation drawer header
                                SettingFragment object = new SettingFragment();
                                object.setHeaderInfo();

                                Toast.makeText(getApplicationContext(), "response msg ---> " + response.body().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "something goes wrong!! ===> " + t.getMessage(), Toast.LENGTH_LONG).show();
                            Log.d("Error 102 --> ", t.getMessage());

                        }
                    });


            }

        });

    }


    /*//username
    boolean UsernameValidate(){
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

    boolean PasswordValidate(){
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

    boolean EmailValidate(){
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

    boolean PhoneValidate(){
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
*/
}

