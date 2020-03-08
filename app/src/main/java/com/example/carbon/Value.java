package com.example.carbon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("COValue")
    @Expose
    private String cOValue;

    @SerializedName("COThreshold")
    @Expose
    private String cOThreshold;

    @SerializedName("Battery")
    @Expose
    private String battery;

    public String getcOValue() {
        return cOValue;
    }

    public void setcOValue(String cOValue) {
        this.cOValue = cOValue;
    }

    public String getcOThreshold() {
        return cOThreshold;
    }

    public void setcOThreshold(String cOThreshold) {
        this.cOThreshold = cOThreshold;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }
}
