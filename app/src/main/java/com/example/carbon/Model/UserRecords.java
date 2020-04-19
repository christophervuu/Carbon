package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserRecords {
    @SerializedName("body")
    @Expose
    private ArrayList<UserList> body = null;

    @SerializedName("FirebaseId")
    @Expose
    private String FirebaseId;

    public UserRecords(String firebaseId) {
        FirebaseId = firebaseId;
    }

    public ArrayList<UserList> getBody() {
        return body;
    }

    public void setBody(ArrayList<UserList> body) {
        this.body = body;
    }
}
