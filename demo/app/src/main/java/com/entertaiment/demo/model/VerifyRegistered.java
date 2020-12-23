package com.entertaiment.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyRegistered {

    @SerializedName("firstname")
    @Expose
    private String mFirstname;

    @SerializedName("lastname")
    @Expose
    private String mLastname;

    @SerializedName("national_id_number")
    @Expose
    private String mNRIC;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("token")
    @Expose
    private String mToken;

    @SerializedName("avatar_url")
    @Expose
    private String mAvaUrl;

    @SerializedName("is_registered")
    @Expose
    private Boolean mIsRegistered;

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

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public Boolean getIsRegistered() {
        return mIsRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        mIsRegistered = isRegistered;
    }

    public String getAvaUrl() {
        return mAvaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        mAvaUrl = avaUrl;
    }
}
