package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.net.URISyntaxException;
import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    public static final String ENTER_CHANGE_PASSWORD = "ENTER_CHANGE_PASSWORD";
    public static final String SAVE = "SAVE";
    public static final String PASSWORD = "PASSWORD";

    BootReceiver b = new BootReceiver();
    //https://coolsymbol.com/
    SharedPreferences save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = getSharedPreferences(SAVE,MODE_PRIVATE);
        Log.i("LOG","CURRENT PASSWORD: " + save.getString(PASSWORD,""));
        Intent service = new Intent(getApplicationContext(), ScreenListenerService.class);
        getApplicationContext().startService(service);

    }

    public void btnChooseSymbolsClick(View view){
        if (save.getString(PASSWORD,"").length()==Password.length) {

            Intent intent = new Intent(this,LockerActivity.class);
            intent.putExtra(ENTER_CHANGE_PASSWORD,true);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, ChooseSymbols.class);
            startActivity(intent);
        }
    }
    public void lockerClick(View view){
        Intent intent = new Intent(this, LockerActivity.class);
        startActivity(intent);

    }
}

