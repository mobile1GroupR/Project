package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectApplications extends AppCompatActivity {

    private static final String TAG = "LOG";

    ArrayList<AppInfo> appsInfo = new ArrayList<>();

    
    List<PackageInfo> packs;
    List<ResolveInfo> appList;
    ArrayList<String> appsNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_applications);
        getApps();
        initRecyclerView();

    }



    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,appsInfo);
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
        appsNames = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            ApplicationInfo a = p.applicationInfo;
            // skip system apps if they shall not be included
            /*if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
            }*/
            Drawable icon = getPackageManager().getApplicationIcon(a);
            String appname = (String) getPackageManager().getApplicationLabel(p.applicationInfo);
            appsNames.add(appname);

            appsInfo.add(new AppInfo(appname,p.packageName,icon));
        }
        Log.i("TEST", appList.toString());

    }
}
