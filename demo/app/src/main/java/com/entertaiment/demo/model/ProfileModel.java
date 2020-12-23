package com.entertaiment.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("firstname")
    @Expose
    private String mFistName;

    @SerializedName("lastname")
    @Expose
    private String mLastName;

    public String getFistName() {
        return mFistName;
    }

    public void setFistName(String fistName) {
        mFistName = fistName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }
}
