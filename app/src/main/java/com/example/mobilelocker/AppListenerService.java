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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class AppListenerService extends Service {
    private static final String TAG = "RUN";
    public static final String HOME_PAGE = "com.google.android.apps.nexuslauncher";
    public static final String BLOCKED_APPS_ARRAY = "BLOCKED_APPS_ARRAY";
    public static final String SAVE = "SAVE";
    String currentApp="";

    ArrayList<String> blockedApps;
    SharedPreferences save;
    Context mContext;

    public AppListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        save = this.getSharedPreferences(SAVE,MODE_PRIVATE);
        Log.i(TAG, "onCreate: APP_LISTENER_SERVICE");
        loadData();
        startTimer();
    }

    private void loadData(){
        Gson gson = new Gson();
        String json = save.getString(BLOCKED_APPS_ARRAY,"");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        blockedApps = gson.fromJson(json,type);

        if (blockedApps==null){
            blockedApps = new ArrayList<>();
        }
        Log.i("TEST8", blockedApps.toString());

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        loadData();
        stopTimerTask();
        Log.i("SERVICE", "onDestroy() , service stopped...");
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
                //Log.i("DELETE", blockedApps.toString());
                currentApp = getCurrentApp();
               // Log.i("CURRENT_APP", "run: " + currentApp);
                if (blockedApps.contains(currentApp) && !unlocked){
                    unlocked = true;
                    Intent intent = new Intent(getApplicationContext(),LockerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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

    public void stopTimerTask(){
        if (timer!=null)
            timer.cancel();
            timer=null;
    }

    private String getCurrentApp(){
        String s = "";
        UsageStatsManager usm = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();

        List<UsageStats> stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*1000, time);

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
