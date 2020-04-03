package com.example.mobilelocker;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenListenerService extends Service {
    private static BootReceiver br;
    public IntentFilter intentFilter;
    public ScreenListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LOG", "onCreate: ScreenListenerService");
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.setPriority(100);
         br = new BootReceiver();
        registerReceiver(br,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (br!=null){
            unregisterReceiver(br);
            Log.i("LOG", "onDestroy: ScreenListenerService");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }


}
