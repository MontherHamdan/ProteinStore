package com.example.proteinstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proteinstore.R;
import com.example.proteinstore.adapter.CartProductAdapter;
import com.example.proteinstore.model.Product;
import com.example.proteinstore.model.ShoppingCart;

import java.util.List;

public class CartShoppingActivity extends AppCompatActivity {
    private ListView cartListView;
    private Button checkoutButton;
    private ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shopping);

       cartListView = findViewById(R.id.cartListView);
        checkoutButton = findViewById(R.id.checkoutButton);
        shoppingCart = new ShoppingCart();

        // Get the list of products from the shopping cart
        List<Product> cartProducts = shoppingCart.getProducts();

        // Create an adapter for the cart products
        final CartProductAdapter cartProductAdapter = new CartProductAdapter(this, cartProducts);
        cartListView.setAdapter(cartProductAdapter);

        // Handle the checkout button click
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),CheackOutActivity.class));

        /*        // Perform checkout process here
                // You can clear the cart or proceed to the payment screen
                cartProducts.clear(); // Clear the list of products
                cartProductAdapter.notifyDataSetChanged(); // Notify the adapter that data has changed
                Toast.makeText(CartShoppingActivity.this, "Checkout completed", Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}