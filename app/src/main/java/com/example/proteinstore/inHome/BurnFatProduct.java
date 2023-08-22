package com.example.proteinstore.inHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proteinstore.R;
import com.example.proteinstore.activities.CheackOutActivity;
import com.example.proteinstore.adapter.ProductAdapter;
import com.example.proteinstore.apis.RetrofitClient;
import com.example.proteinstore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BurnFatProduct extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_fat_product);
        //initialization
        listView=(ListView) findViewById(R.id.listview);

        Call<List<Product>> call = RetrofitClient.getInstance().getMyApi().getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            // if the api return response
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList= response.body();
                //create new adapter to pass productList argument to there
                ProductAdapter adapter = new ProductAdapter(getApplicationContext(),productList);
                listView.setAdapter(adapter);
                //Toast.makeText(getContext(),"connect successfully",Toast.LENGTH_LONG).show();

            }

            @Override
            //if the api does not return response
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                Log.d("Retrofit ERROR -->",t.getMessage());

            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    startActivity(new Intent(getApplicationContext(), CheackOutActivity.class));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}