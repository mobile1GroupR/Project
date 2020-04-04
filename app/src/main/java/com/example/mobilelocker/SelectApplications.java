package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SelectApplications extends AppCompatActivity {

    private static final String TAG = "LOG";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> imgs = new ArrayList<>();

    
    List<PackageInfo> packs;
    List<ResolveInfo> appList;
    List apps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_applications);
        getApps();
        initRecyclerView();

    }


    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(names,imgs,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getApps(){
        PackageManager packageManager = getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        appList = packageManager.queryIntentActivities(mainIntent, 0);
        //Collections.sort(appList, new ResolveInfo.DisplayNameComparator(packageManager));
        packs = packageManager.getInstalledPackages(0);
        apps = new ArrayList();
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            ApplicationInfo a = p.applicationInfo;
            // skip system apps if they shall not be included
            if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
            }
            names.add(p.packageName);

        }
        Log.i("LOGCHECK", appList.toString());
        Log.i("LOGCHECK", names.toString());

    }
}
