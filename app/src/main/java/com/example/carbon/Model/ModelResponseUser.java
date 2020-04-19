package com.example.carbon.Model;

public class ModelResponseUser {


    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private String FirebaseId;

    public ModelResponseUser(String firstName, String lastName, String email, String phone, String firebaseId) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
        FirebaseId = firebaseId;
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

    public String getFirebaseId() {
        return FirebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        FirebaseId = firebaseId;
    }
}
