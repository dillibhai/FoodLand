package com.machamasisuraj.foodland.Bll;

import android.content.Context;
import android.widget.Toast;

import com.machamasisuraj.foodland.Api.ItemApi;
import com.machamasisuraj.foodland.Api.RetrofitCaller;
import com.machamasisuraj.foodland.Response.ImageResponse;
import com.machamasisuraj.foodland.StrictMode.StrictModeClass;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ImageUploadBll {
    private String imagepath;
    private Context mContext;

    public ImageUploadBll(String imagepath, Context mContext) {
        this.imagepath = imagepath;
        this.mContext = mContext;

    }

    public boolean uploadFile() {

        File file = new File(imagepath);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/from-data"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);

        ItemApi itemApi= RetrofitCaller.getInstance().create(ItemApi.class);
        Call<ImageResponse> imageResponseCall= itemApi.uploadImage(part);
        StrictModeClass.StrictMode();
        try {
            if(imageResponseCall.execute().isSuccessful()){
                Toast.makeText(mContext, "Image Upload Successfull", Toast.LENGTH_SHORT).show();
                    return true;
            }
            } catch (IOException e) {
            e.printStackTrace();
                return false;
            }
            return false;

    }
}
