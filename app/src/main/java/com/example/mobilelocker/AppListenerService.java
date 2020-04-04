package com.example.mobilelocker;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class AppListenerService extends Service {
    private static final String TAG = "RUN";
    public static final String HOME_PAGE = "com.google.android.apps.nexuslauncher";
    public static final String BLOCKED_APPS = "BLOCKED_APPS";
    public static final String SAVE = "SAVE";
    String currentApp="";

    Set blockedApps;
    SharedPreferences save;
    Context mContext;

    public AppListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        save = this.getSharedPreferences(SAVE,MODE_PRIVATE);
        Log.i(TAG, "onCreate: APPLISTENERSERVICE");
        blockedApps = save.getStringSet(BLOCKED_APPS,new HashSet<String>());
        startTimer();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Timer timer;
    private TimerTask timerTask;
    boolean unlocked;
    public void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask(){

            @Override
            public void run() {
                currentApp = getCurrentApp();
                Log.i("CURRENT_APP", "run: " + currentApp);
            /*    for (String s: App.names) {
                    if (currentApp.compareTo(s)==0 && unlocked){
                        Intent intent = new Intent(getApplicationContext(),LockerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }*/
                if (blockedApps.contains(currentApp) && !unlocked){
                    unlocked = true;
                    Intent intent = new Intent(getApplicationContext(),LockerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                if (HOME_PAGE.compareTo(currentApp)==0){
                    unlocked = false;
                }
                Log.i(TAG, unlocked+"");

                Log.i(TAG, blockedApps.toString());

            }
        };
        timer.schedule(timerTask,0,800);
    }

    private String getCurrentApp(){
        String s = "";
        UsageStatsManager usm = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();

// We get usage stats for the last 10 seconds
        List<UsageStats> stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*1000, time);

// Sort the stats by the last time used
        if(stats != null) {
            SortedMap<Long,UsageStats> mySortedMap = new TreeMap<>();
            for (UsageStats usageStats : stats) {
                mySortedMap.put(usageStats.getLastTimeUsed(),usageStats);
            }
            if(mySortedMap != null && !mySortedMap.isEmpty()) {
                s= mySortedMap.get(mySortedMap.lastKey()).getPackageName();
            }
        }

        return s;

    }
}
