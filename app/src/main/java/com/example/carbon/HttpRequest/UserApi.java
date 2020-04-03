package com.example.carbon.HttpRequest;

import com.example.carbon.Model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("SignUp")
    Call<UserProfile> getPosts(@Body UserProfile userProfile);

}
