package com.example.carbon.HttpRequest;

import com.example.carbon.Model.DeviceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    /*
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);
    */

    @GET("devices/db")
    Call<DeviceResponse> getDeviceResponse();
}
