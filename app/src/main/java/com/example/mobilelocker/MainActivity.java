package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LOG";
    public static final String SAVE = "SAVE";
    public static final String PASSWORD = "PASSWORD";

    //https://coolsymbol.com/
    SharedPreferences save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = getSharedPreferences(SAVE,MODE_PRIVATE);
        Log.i("LOG","CURRENT PASSWORD: " + save.getString(PASSWORD,""));

        getPermission();
        Log.i(TAG, "GET PERMISSION: " + getPermission());

        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
        Intent service2 = new Intent(getApplicationContext(), AppListenerService.class);
        getApplicationContext().startService(service2);


    }
    public boolean getPermission(){

        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) this.getSystemService(Context.APP_OPS_SERVICE);
            int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName);
            return (mode != AppOpsManager.MODE_ALLOWED);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void btnChooseSymbolsClick(View view){

        Log.i(TAG, App.names.toString());

        if (save.getString(PASSWORD,"").equals("")) {
            Intent intent = new Intent(this, ChooseSymbols.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this,CheckPasswordBeforeChange.class);
            startActivity(intent);
        }
    }
    public void lockerClick(View view){
        if (save.getString(PASSWORD,"").equals("")){
            Toast.makeText(this, "First Create Password", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, SelectApplications.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}

