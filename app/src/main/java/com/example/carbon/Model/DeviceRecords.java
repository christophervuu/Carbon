package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeviceRecords {
    @SerializedName("body")
    @Expose
    private ArrayList<DeviceList> body = null;

    @SerializedName("FirebaseId")
    @Expose
    private String FirebaseId;

    public DeviceRecords(String firebaseId) {
        FirebaseId = firebaseId;
    }

    public ArrayList<DeviceList> getBody() {
        return body;
    }

    public void setBody(ArrayList<DeviceList> body) {
        this.body = body;
    }
}
