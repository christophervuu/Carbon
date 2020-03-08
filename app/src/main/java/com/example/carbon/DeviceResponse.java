package com.example.carbon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceResponse {
    @SerializedName("info")
    @Expose
    private List<Info> info = null;

    @SerializedName("values")
    @Expose
    private List<Value> values = null;

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
