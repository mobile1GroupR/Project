package com.example.mobilelocker;

import android.graphics.drawable.Icon;

import androidx.annotation.NonNull;

public class AppInfo {

    private String name;
    private String packageName;
    private Icon icon;

    public AppInfo(String name, String packageName, Icon icon) {
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


    public Icon getIcon() {
        return icon;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name:" + this.getName() + "  PackageName:" + this.getPackageName();
    }
}
