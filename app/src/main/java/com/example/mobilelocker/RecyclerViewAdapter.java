package com.example.mobilelocker;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "LOG";
    public static final String BLOCKED_APPS = "BLOCKED_APPS";
    public static final String SAVE = "SAVE";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> imgs = new ArrayList<>();
    private Context mContext;

    SharedPreferences save ;

    public RecyclerViewAdapter(ArrayList<String> names, ArrayList<String> imgs, Context mContext) {
        this.names = names;
        this.imgs = imgs;
        this.mContext = mContext;
        save = mContext.getSharedPreferences(SAVE,Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Set blockedApps = save.getStringSet(BLOCKED_APPS,new HashSet<String>());
        holder.name.setText(names.get(position));
        if(blockedApps.contains(names.get(position))){
            holder.name.setTextColor(Color.rgb(200,0,0));
        }
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(blockedApps.contains(names.get(position)) || App.names.contains((names.get(position))))   {
                        Log.i(TAG, "NOT ADDED");
                        return;
                    }
                    Log.i(TAG, "onClick Added: " + position);
                    App.names.add(names.get(position));
                    SharedPreferences.Editor editor = save.edit();
                    editor.putStringSet(BLOCKED_APPS, App.names);
                    editor.apply();
                    holder.name.setTextColor(Color.rgb(200,0,0));
                    mContext.stopService(new Intent(mContext, AppListenerService.class));
                    Intent service2 = new Intent(mContext, AppListenerService.class);
                    mContext.startService(service2);
                }
            });
    }

    @Override
    public int getItemCount() {
        return names.size();
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
