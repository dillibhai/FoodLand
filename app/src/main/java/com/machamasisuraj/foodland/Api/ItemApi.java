package com.machamasisuraj.foodland.Api;
import com.machamasisuraj.foodland.Model.Item;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemApi {
    @GET("/item/all")
    Call<Item> getAllItemsLIst();
}
