package com.example.carbon.Model;

public class ModelCreateUserAccount {
    private String UserId;
    private String AccountId;
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

    public ModelCreateUserAccount(String userId, String accountId, String firstName, String lastName, String birthDate, String email, String phone, Boolean canEmail, Boolean canText, Boolean canCall, Boolean isMaster, String firebaseId, String createdOn, String updatedOn) {
        UserId = userId;
        AccountId = accountId;
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        Email = email;
        Phone = phone;
        CanEmail = canEmail;
        CanText = canText;
        CanCall = canCall;
        IsMaster = isMaster;
        FirebaseId = firebaseId;
        CreatedOn = createdOn;
        UpdatedOn = updatedOn;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountID) {
        AccountId = accountID;
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
