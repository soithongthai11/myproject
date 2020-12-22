package com.entertaiment.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class Constraints {

    public static final String APP_NAME = "DEMO";

    public static final List<String> getPhoneCode() {
        return new ArrayList<String>() {{
            add("+30");
            add("+44");
            add("+65");
            add("+84");
        }};
    }
}
