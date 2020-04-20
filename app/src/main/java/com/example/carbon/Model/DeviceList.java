package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceList {

    @SerializedName("deviceID")
    @Expose
    private String DeviceId;

    @SerializedName("accountID")
    @Expose
    private String AccountId;

    @SerializedName("type")
    @Expose
    private String Type;

    @SerializedName("valueCO")
    @Expose
    private String ValueCO;

    @SerializedName("name")
    @Expose
    private String Name;

    public DeviceList(String deviceId, String accountId, String type, String valueCO, String name) {
        DeviceId = deviceId;
        AccountId = accountId;
        Type = type;
        ValueCO = valueCO;
        Name = name;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getValueCO() {
        return ValueCO;
    }

    public void setValueCO(String valueCO) {
        ValueCO = valueCO;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
