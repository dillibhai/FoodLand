package com.machamasisuraj.foodland.Bll;

import android.util.Log;

import com.machamasisuraj.foodland.Api.ItemApi;
import com.machamasisuraj.foodland.Api.RetrofitCaller;
import com.machamasisuraj.foodland.Model.Item;
import com.machamasisuraj.foodland.StrictMode.StrictModeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ItemBll {
     List<Item> itemList=  new ArrayList<>();

    public  List<Item> getAllItems() {

         ItemApi itemApi  = RetrofitCaller.getInstance().create(ItemApi.class);
        Call<List<Item>> itemsCall = itemApi.getAllItemsLIst();
        StrictModeClass.StrictMode();
        try {
            itemList=  itemsCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
