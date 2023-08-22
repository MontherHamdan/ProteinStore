package com.example.proteinstore.apis;

import com.example.proteinstore.model.Product;
import com.example.proteinstore.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("API.php")
    Call<List<Product>> getProduct();

    /* the SignIn call */
    @FormUrlEncoded
    @POST("checkUser.php")
    Call<Result> userLogin(
        @Field("email") String email,
        @Field("password") String password
    );
    /*The SignUp Call */
    @FormUrlEncoded
    @POST("InsertUser.php")
    Call<Result>  insertUser(
            @Field("name") String name,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone

    );
    /*the update call */
    @FormUrlEncoded
    @POST("updateUser.php")
    Call<Result> updateUser(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    /*the Delete call */
    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<Result> deleteUser(
            @Field("id") int id

    );
}
