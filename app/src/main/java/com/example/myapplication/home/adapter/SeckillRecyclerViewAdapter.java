package com.example.myapplication.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHodler> {

    public class ViewHodler extends RecyclerView.ViewHolder {
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
