package com.example.carbon.HttpRequest;

import com.example.carbon.Model.DeviceProfile;
import com.example.carbon.Model.DeviceRecords;
import com.example.carbon.Model.ModelAddDevice;
import com.example.carbon.Model.ModelAddUser;
import com.example.carbon.Model.ModelCreateUserAccount;
import com.example.carbon.Model.ModelResponseUser;
import com.example.carbon.Model.ModelUpdateUser;
import com.example.carbon.Model.UserRecords;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("create")
    Call<ModelCreateUserAccount> createUser(@Body ModelCreateUserAccount modelCreateUserAccount);

    @POST("add")
    Call<ModelAddUser> addUser(@Body ModelAddUser modelAddUser);

    @POST("update")
    Call<ModelUpdateUser> updateUser(@Body ModelUpdateUser modelUpdateUser);

    @POST("profile")
    Call<ModelResponseUser> getProfile(@Body ModelResponseUser modelResponseUser);

    @POST("getaccountusers")
    Call<UserRecords> getaccountusers(@Body UserRecords userRecords);

    @POST("list")
    Call<DeviceRecords> getDeviceList(@Body DeviceRecords deviceRecords);

    @POST("profile")
    Call<DeviceProfile> getDeviceProfile(@Body DeviceProfile deviceProfile);

    @POST("adddevices")
    Call<ModelAddDevice> addDevice(@Body ModelAddDevice modelAddDevice);
}
