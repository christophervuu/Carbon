package com.example.carbon.HttpRequest;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @POST("SignUp")
    Call<List<Post>> getPosts(@Query("userId") int userId);

}
