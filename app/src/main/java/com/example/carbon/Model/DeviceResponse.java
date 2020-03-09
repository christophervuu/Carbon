package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeviceResponse {
    @SerializedName("info")
    @Expose
    private ArrayList<Info> info = null;

    @SerializedName("values")
    @Expose
    private ArrayList<Value> values = null;

    public ArrayList<Info> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Info> info) {
        this.info = info;
    }

    public ArrayList<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }
}
