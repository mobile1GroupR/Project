package com.example.mobilelocker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "LOG";
    public static final String BLOCKED_APPS = "BLOCKED_APPS";
    public static final String BLOCKED_APPS_ARRAY = "BLOCKED_APPS_ARRAY";
    public static final String SAVE = "SAVE";


    private ArrayList<AppInfo> appsInfo;
    private ArrayList<String> blockedApps = new ArrayList<>();
    private Context mContext;

    SharedPreferences save;


    public RecyclerViewAdapter(Context mContext, ArrayList<AppInfo> appsInfo) {

        this.appsInfo = appsInfo;
        this.mContext = mContext;
        save = mContext.getSharedPreferences(SAVE, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        loadData();
        holder.name.setText(appsInfo.get(position).getName());
        for (String s : blockedApps) {
            App.names.add(s);
        }
        if (blockedApps.contains(appsInfo.get(position).getPackageName())) {
            Log.i("BLOCKED APPS", appsInfo.get(position).getPackageName());
            holder.name.setTextColor(Color.rgb(200, 0, 0));
        }
        Log.i("TEST4", "A.names: " + App.names.toString());
        Log.i("TEST4", "Blocked Apps:" + blockedApps.toString());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if (blockedApps.contains(appsInfo.get(position).getPackageName()) || App.names.contains((appsInfo.get(position).getPackageName()))) {
                    Log.i(TAG, "REMOVED");
                    App.names.remove(appsInfo.get(position).getPackageName());
                    saveData();
                    loadData();
                    Log.i("TEST7", App.names.toString());
                    holder.name.setTextColor(R.color.colorPrimary);

                } else {
                    Log.i(TAG, "onClick Added: " + position);
                    App.names.add(appsInfo.get(position).getPackageName());
                    saveData();
                    Log.i("TEST7", App.names.toString());
                    holder.name.setTextColor(Color.rgb(200, 0, 0));
                }

                mContext.stopService(new Intent(mContext, AppListenerService.class));
                Intent service2 = new Intent(mContext, AppListenerService.class);
                mContext.startService(service2);
            }
        });
    }

    public void saveData() {
        SharedPreferences.Editor editor = save.edit();
        Gson gson = new Gson();
        String json = gson.toJson(App.names);
        editor.putString(BLOCKED_APPS_ARRAY, json);
        editor.apply();
    }

    private void loadData() {
        Gson gson = new Gson();
        String json = save.getString(BLOCKED_APPS_ARRAY, "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        blockedApps = gson.fromJson(json, type);

        if (blockedApps == null) {
            blockedApps = new ArrayList<>();
        }
        Log.i("TEST5", blockedApps.toString());

    }

    @Override
    public int getItemCount() {
        return appsInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout layout;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.itemLayout);
            name = itemView.findViewById(R.id.nameHolder);
            img = itemView.findViewById(R.id.imageView);

        }
    }
}
