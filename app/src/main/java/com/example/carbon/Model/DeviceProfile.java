package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceProfile {

    @SerializedName("deviceID")
    @Expose
    private String DeviceId;

    @SerializedName("type")
    @Expose
    private String Type;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("valueCO")
    @Expose
    private String ValueCO;

    @SerializedName("devEUI")
    @Expose
    private String DevEUI;

    @SerializedName("updatedOn")
    @Expose
    private String UpdatedOn;

    @SerializedName("battery")
    @Expose
    private String Battery;

    public DeviceProfile(String deviceId) {
        DeviceId = deviceId;
        Type = null;
        Name = null;
        ValueCO = null;
        DevEUI = null;
        UpdatedOn = null;
        Battery = null;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValueCO() {
        return ValueCO;
    }

    public void setValueCO(String valueCO) {
        ValueCO = valueCO;
    }

    public String getDevEUI() {
        return DevEUI;
    }

    public void setDevEUI(String devEUI) {
        DevEUI = devEUI;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }
}
