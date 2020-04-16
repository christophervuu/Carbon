package com.example.carbon.HttpRequest;

import com.example.carbon.Model.ModelAddUser;
import com.example.carbon.Model.ModelCreateUserAccount;
import com.example.carbon.Model.ModelGetAccountUsers;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @POST("getaccountusers")
    Call<ModelGetAccountUsers> getAccountUsers(@Body ModelGetAccountUsers modelGetAccountUsers);

    @POST("create")
    Call<ModelCreateUserAccount> createUser(@Body ModelCreateUserAccount modelCreateUserAccount);

    @POST("add")
    Call<ModelAddUser> addUser(@Body ModelAddUser modelAddUser);

}
