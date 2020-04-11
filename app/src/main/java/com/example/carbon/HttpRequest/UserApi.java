package com.example.carbon.HttpRequest;

import com.example.carbon.Model.ModelCreateUserAccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("getUsers")
    Call<ModelCreateUserAccount> getUsers();

    @POST("create2")
    Call<ModelCreateUserAccount> createUser(@Body ModelCreateUserAccount modelCreateUserAccount);

}
