package com.example.mobilelocker;

public class Password {

    private static String password;
    public static final int length=4;

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Password.password = password;
    }
}
