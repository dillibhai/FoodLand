package com.dillibhandari.foodmandu.Bll;
import android.util.Log;

import com.dillibhandari.foodmandu.Api.RetrofitCaller;
import com.dillibhandari.foodmandu.Api.UsersAPI;
import com.dillibhandari.foodmandu.Response.SignUpResponse;
import com.dillibhandari.foodmandu.Url.Url;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        UsersAPI usersAPI = RetrofitCaller.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(username, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                Log.d("token received", "checkUser: "+ Url.token);
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
