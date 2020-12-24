package com.entertaiment.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("firstname")
    @Expose
    private String mFirstname;

    @SerializedName("lastname")
    @Expose
    private String mLastname;

    @SerializedName("id_number")
    @Expose
    private String mNRIC;

    @SerializedName("email")
    @Expose
    private String mEmail;

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public String getNRIC() {
        return mNRIC;
    }

    public void setNRIC(String NRIC) {
        mNRIC = NRIC;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
