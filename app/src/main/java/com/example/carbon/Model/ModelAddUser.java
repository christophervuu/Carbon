package com.example.carbon.Model;

public class ModelAddUser {
    private String Email;
    private String FirebaseId;
    private String UpdatedOn;

    public ModelAddUser(String email, String firebaseId, String updatedOn) {
        Email = email;
        FirebaseId = firebaseId;
        UpdatedOn = updatedOn;
    }

    public String getEmailAddUser() {
        return Email;
    }

    public void setEmailAddUser(String email) {
        Email = email;
    }

    public String getFirebaseId() {
        return FirebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        FirebaseId = firebaseId;
    }

    public String getUpdatedOnAddUser() {
        return UpdatedOn;
    }

    public void setUpdatedOnAddUser(String updatedOn) {
        UpdatedOn = updatedOn;
    }
}
