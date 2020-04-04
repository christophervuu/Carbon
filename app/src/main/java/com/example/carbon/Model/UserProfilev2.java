package com.example.carbon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfilev2 {
    @SerializedName("userID")
    @Expose
    private String UserId;

    @SerializedName("accountID")
    @Expose
    private String AccountID;

    @SerializedName("firstName")
    @Expose
    private String FirstName;

    @SerializedName("lastName")
    @Expose
    private String LastName;

    @SerializedName("birthDate")
    @Expose
    private String BirthDate;

    @SerializedName("email")
    @Expose
    private String Email;

    @SerializedName("phone")
    @Expose
    private String Phone;

    @SerializedName("canEmail")
    @Expose
    private int CanEmail;

    @SerializedName("canText")
    @Expose
    private int CanText;

    @SerializedName("canCall")
    @Expose
    private int CanCall;

    @SerializedName("isMaster")
    @Expose
    private int IsMaster;

    @SerializedName("firebaseID")
    @Expose
    private int FirebaseID;

    @SerializedName("createdOn")
    @Expose
    private String CreatedOn;

    @SerializedName("UpdatedOn")
    @Expose
    private String UpdatedOn;

    public UserProfilev2(String userId, String accountID, String firstName, String lastName, String birthDate, String email, String phone, int canEmail, int canText, int canCall, int isMaster, int firebaseID, String createdOn, String updatedOn) {
        UserId = userId;
        AccountID = accountID;
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        Email = email;
        Phone = phone;
        CanEmail = canEmail;
        CanText = canText;
        CanCall = canCall;
        IsMaster = isMaster;
        FirebaseID = firebaseID;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
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

    public int getCanEmail() {
        return CanEmail;
    }

    public void setCanEmail(int canEmail) {
        CanEmail = canEmail;
    }

    public int getCanText() {
        return CanText;
    }

    public void setCanText(int canText) {
        CanText = canText;
    }

    public int getCanCall() {
        return CanCall;
    }

    public void setCanCall(int canCall) {
        CanCall = canCall;
    }

    public int getIsMaster() {
        return IsMaster;
    }

    public void setIsMaster(int isMaster) {
        IsMaster = isMaster;
    }

    public int getFirebaseID() {
        return FirebaseID;
    }

    public void setFirebaseID(int firebaseID) {
        FirebaseID = firebaseID;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }
}