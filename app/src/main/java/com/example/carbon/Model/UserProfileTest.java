package com.example.carbon.Model;

public class UserProfileTest {
    private String UserId;
    private String AccountID;
    private String FirstName;
    private String LastName;
    private String BirthDate;
    private String Email;
    private String Phone;
    private String FirebaseId;

    public UserProfileTest(String userId, String accountID, String firstName, String lastName, String birthDate, String email, String phone, String firebaseId) {
        UserId = userId;
        AccountID = accountID;
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        Email = email;
        Phone = phone;
        FirebaseId = firebaseId;
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

    public String getFirebaseId() { return FirebaseId;}

    public void setFirebaseId(String firebaseId) { FirebaseId = firebaseId;}
}
