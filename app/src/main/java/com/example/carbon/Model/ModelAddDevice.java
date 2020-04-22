package com.example.carbon.Model;

public class ModelAddDevice {

    private String FirebaseId;
    private String DevEUI;
    private String Name;

    public ModelAddDevice(String firebaseId, String devEUI, String name) {
        FirebaseId = firebaseId;
        DevEUI = devEUI;
        Name = name;
    }

    public String getFirebaseId() {
        return FirebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        FirebaseId = firebaseId;
    }

    public String getDevEUI() {
        return DevEUI;
    }

    public void setDevEUI(String devEUI) {
        DevEUI = devEUI;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
