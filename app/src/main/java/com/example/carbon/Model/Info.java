package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("DeviceId")
    @Expose
    private String deviceId;

    @SerializedName("DeviceType")
    @Expose
    private String deviceType;

    @SerializedName("DeviceName")
    @Expose
    private String deviceName;

    @SerializedName("UpdatedOn")
    @Expose
    private String updatedOn;

    @SerializedName("DeviceValue")
    @Expose
    private String deviceValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getDeviceValue() {
        return deviceValue;
    }

    public void setDeviceValue(String updatedOn) {
        this.deviceValue = deviceValue;
    }
}
