package com.thetrendybazaar.thetrendybazaar;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    

    public ItemAdapter() {
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder{

    public ItemViewHolder(View itemView) {
        super(itemView);
    }
}

