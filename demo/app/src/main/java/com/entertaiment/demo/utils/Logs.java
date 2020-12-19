package com.entertaiment.demo.utils;

import android.util.Log;

public class Logs {

    public static void v(String classname, String message) {
        Log.v(Constraints.APP_NAME, classname + ": " + message);
    }

    public static void d(String classname, String message) {
        Log.d(Constraints.APP_NAME, classname + ": " + message);
    }

    public static void i(String classname, String message) {
        Log.i(Constraints.APP_NAME, classname + ": " + message);
    }

    public static void w(String classname, String message) {
        Log.w(Constraints.APP_NAME, classname + ": " + message);
    }

    public static void e(String classname, String message) {
        Log.e(Constraints.APP_NAME, classname + ": " + message);
    }
}
