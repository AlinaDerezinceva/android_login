
package com.example.app_login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.POST;

public interface ServerApi {


    @GET("product/categories/{category}")
    Call<List<Product>> getProductsByCategory(@Path("category") String category);


    @POST("user")
    Call<User> addUser(@Body User user);


    @POST("login")
    Call<User> loginUser(@Body User user);
}



