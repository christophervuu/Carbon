package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {
    @SerializedName("firstName")
    @Expose
    private String FirstName;

    @SerializedName("lastName")
    @Expose
    private String LastName;

    public UserList(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
