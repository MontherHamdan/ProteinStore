package com.example.proteinstore.adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proteinstore.R;
import com.example.proteinstore.model.Product;

import java.util.List;

public class CartProductAdapter extends ArrayAdapter<Product> {
    private List<Product> productList;

    public CartProductAdapter(Context context, List<Product> products) {
        super(context, R.layout.cart_item, products);
        this.productList = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.cartItemName);
        TextView priceTextView = convertView.findViewById(R.id.cartItemPrice);

        Product product = productList.get(position);

        nameTextView.setText(product.getName());
        priceTextView.setText(product.getPrice());

        return convertView;
    }
}