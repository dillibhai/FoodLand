package com.machamasisuraj.foodland.Bll;

import android.util.Log;

import com.machamasisuraj.foodland.Api.ItemApi;
import com.machamasisuraj.foodland.Api.RetrofitCaller;
import com.machamasisuraj.foodland.Model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ItemBll {
    List<Item> itemList=  new ArrayList<>();

    public List<Item> getAllItems() {

         ItemApi itemApi  = RetrofitCaller.getInstance().create(ItemApi.class);
        Call<Item> itemsCall = itemApi.getAllItemsLIst();

        try {
            Response<Item> response = itemsCall.execute();
            if (response.isSuccessful()) {
                itemList.add(response.body());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
