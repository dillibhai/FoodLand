package com.machamasisuraj.foodland.Bll;
import android.util.Log;
import android.widget.Toast;

import com.machamasisuraj.foodland.Api.RetrofitCaller;
import com.machamasisuraj.foodland.Api.UsersAPI;
import com.machamasisuraj.foodland.Response.SignUpResponse;
import com.machamasisuraj.foodland.Url.Url;
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
