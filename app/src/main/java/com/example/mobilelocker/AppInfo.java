package com.example.mobilelocker;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

import androidx.annotation.NonNull;

public class AppInfo {

    private String name;
    private String packageName;
    private Drawable icon;

    public AppInfo(String name, String packageName, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
    }

    public AppInfo(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }


    public String getPackageName() {
        return packageName;
    }


    public Drawable getIcon() {
        return icon;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name:" + this.getName() + "  PackageName:" + this.getPackageName();
    }
}
