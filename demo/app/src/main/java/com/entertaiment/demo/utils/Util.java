package com.entertaiment.demo.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Util {

    public static boolean checkValidPhoneNumber(String phoneCode, String phoneNumber) {
        if(TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 6 || phoneNumber.length() > 13) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phoneNumber).matches();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isEmpty(CharSequence target) {
        return (TextUtils.isEmpty(target));
    }
}
