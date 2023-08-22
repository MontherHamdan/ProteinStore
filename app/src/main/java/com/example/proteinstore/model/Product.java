package com.example.proteinstore.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("img")
    String img;

    public Product(String name, String price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
}
