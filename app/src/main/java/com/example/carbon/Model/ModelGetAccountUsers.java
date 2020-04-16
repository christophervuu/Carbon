package com.example.carbon.Model;

public class ModelGetAccountUsers {
    private String FirebaseId;

    public ModelGetAccountUsers(String firebaseId) {
        FirebaseId = firebaseId;
    }

    public String getFirebaseId() {
        return FirebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        FirebaseId = firebaseId;
    }
}
