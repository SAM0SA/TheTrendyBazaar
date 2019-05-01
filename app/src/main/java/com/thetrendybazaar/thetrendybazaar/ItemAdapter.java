package com.thetrendybazaar.thetrendybazaar;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    List<Item> items;
    LayoutInflater inflater;

    public ItemAdapter(Context context, List<Item> items) {
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position).name);
        //int reviewCount = DatabaseManager.reviews.select()


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView itemTitle;
    TextView itemReviewCount;


    public ItemViewHolder(View itemView) {
        super(itemView);
        itemTitle = itemView.findViewById(R.id.listing_title);
        itemReviewCount = itemView.findViewById(R.id.listing_reviews);
    }
}

