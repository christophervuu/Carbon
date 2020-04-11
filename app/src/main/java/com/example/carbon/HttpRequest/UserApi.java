package com.example.carbon.HttpRequest;

import com.example.carbon.Model.ModelAddUser;
import com.example.carbon.Model.ModelCreateUserAccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @GET("getUsers")
    Call<ModelCreateUserAccount> getUsers(@Query("FirebaseId") int FirebaseId);

    @POST("create")
    Call<ModelCreateUserAccount> createUser(@Body ModelCreateUserAccount modelCreateUserAccount);

    @POST("add")
    Call<ModelAddUser> addUser(@Body ModelAddUser modelAddUser);

}
