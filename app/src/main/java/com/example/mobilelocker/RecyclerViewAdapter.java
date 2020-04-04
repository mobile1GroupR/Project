package com.example.mobilelocker;

import android.content.Context;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "LOG";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> imgs = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> names, ArrayList<String> imgs, Context mContext) {
        this.names = names;
        this.imgs = imgs;
        this.mContext = mContext;
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
        //Log.i(TAG, "onBindViewHolder: called");


        holder.name.setText(names.get(position));
        if(App.names.contains(names.get(position))){
            holder.name.setTextColor(Color.rgb(200,0,0));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + position);
                App.names.add(names.get(position));
                holder.name.setTextColor(Color.rgb(200,0,0));
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
