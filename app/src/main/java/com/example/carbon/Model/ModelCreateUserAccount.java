package com.example.carbon.Model;

import java.time.Instant;
import java.util.UUID;

public class ModelCreateUserAccount {
    private String UserId;
    private String AccountID;
    private String FirstName;
    private String LastName;
    private String BirthDate;
    private String Email;
    private String Phone;
    private Boolean CanEmail;
    private Boolean CanText;
    private Boolean CanCall;
    private Boolean IsMaster;
    private String FirebaseId;
    private String CreatedOn;
    private String UpdatedOn;

    public ModelCreateUserAccount(String firstName, String lastName, String birthDate, String email, String firebaseId) {
        UserId = UUID.randomUUID().toString();
        AccountID = null;
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        Email = email;
        Phone = null;
        CanEmail = false;
        CanText = false;
        CanCall = false;
        IsMaster = false;
        FirebaseId = firebaseId;
        CreatedOn = Instant.now().toString();
        UpdatedOn = Instant.now().toString();
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

    public Boolean getCanEmail() { return CanEmail; }

    public void setCanEmail(Boolean canEmail) { CanEmail = canEmail; }

    public Boolean getCanText() { return CanText; }

    public void setCanText(Boolean canText) { CanText= canText; }

    public Boolean getCanCall() { return CanCall; }

    public void setCanCall(Boolean canCall) { CanCall = canCall; }

    public Boolean getMaster() { return IsMaster; }

    public void setMaster(Boolean master) { IsMaster = master; }

    public String getFirebaseId() { return FirebaseId;}

    public void setFirebaseId(String firebaseId) { FirebaseId = firebaseId;}

    public String getCreatedOn() { return CreatedOn; }

    public void setCreatedOn(String createdOn) { CreatedOn = createdOn; }

    public String getUpdatedOn() { return UpdatedOn; }

    public void setUpdatedOn(String updatedOn) { UpdatedOn = updatedOn; }
}
