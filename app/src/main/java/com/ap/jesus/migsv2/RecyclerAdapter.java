package com.ap.jesus.migsv2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<POI> items;
   // private final FragmentActivity mActivity;
    OnItemClickListener mItemClickListener;


    public RecyclerAdapter(List<POI> items) {
        this.items = items;
        //this.mActivity = mActivity;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.poi_card, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {

        viewHolder.image.setImageResource(items.get(i).getImage());
        viewHolder.name.setText(items.get(i).getName());
        viewHolder.preferences.setText(items.get(i).getPreferences());
        int time = items.get(i).gettime();
        if (time == 1) {
            viewHolder.time.setText("Tiempo: " + Integer.toString(time) + " minuto");
        }else{
            viewHolder.time.setText("Tiempo: " + Integer.toString(time) + " minutos");
        }
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item

        ImageView image;
        TextView name;
        TextView preferences;
        TextView time;


        public RecyclerViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.card_image);
            preferences = (TextView) v.findViewById(R.id.card_preferences);
            name= (TextView) v.findViewById(R.id.card_name);
            time = (TextView) v.findViewById(R.id.card_time);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null){
                mItemClickListener.onItemClick(v,getAdapterPosition());
            }
/*            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            String item = items.get(itemPosition);
            Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();*/

        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}


