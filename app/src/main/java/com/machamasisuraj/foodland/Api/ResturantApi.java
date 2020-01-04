package com.machamasisuraj.foodland.Api;

import com.machamasisuraj.foodland.Model.Resturant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ResturantApi {
@GET("/resturant/all")
    Call<Resturant> getAllResturant();

@POST("/resturant/create")
    Call<Resturant> insertResturant(@Body Resturant resturant, @Header("Authorization") String token);

}
