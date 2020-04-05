package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileTest {
    @SerializedName("UserID")
    @Expose
    private String UserId;

    @SerializedName("AccountID")
    @Expose
    private String AccountID;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("LastName")
    @Expose
    private String LastName;

    @SerializedName("BirthDate")
    @Expose
    private String BirthDate;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("Phone")
    @Expose
    private String Phone;

    public UserProfileTest(String userId, String accountID, String firstName, String lastName, String birthDate, String email, String phone) {
        UserId = userId;
        AccountID = accountID;
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        Email = email;
        Phone = phone;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
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

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}