package com.example.carbon.HttpRequest;

import com.example.carbon.Model.UserProfileTest;
import com.example.carbon.Model.UserProfilev2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("create")
    Call<UserProfileTest> createUser(@Body UserProfileTest userProfileTest);

}
