package com.example.mobilelocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_SCREEN_OFF)) {
            Log.i("LOG","Screen went OFF");
        } else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_SCREEN_ON)) {
            Log.i("LOG","Screen went ON");
            Intent intent2 = new Intent();
            intent2.setClassName("com.example.mobilelocker","com.example.mobilelocker.LockerActivity");
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }


    }
}
