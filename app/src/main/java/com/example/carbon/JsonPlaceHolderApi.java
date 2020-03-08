package com.example.carbon;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
