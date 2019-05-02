package com.thetrendybazaar.thetrendybazaar;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    List<Item> items;
    LayoutInflater inflater;
    Context context;

    public ItemAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
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
        Item item = items.get(position);
        holder.itemTitle.setText(items.get(position).name);
        int reviewCount = DatabaseManager.reviews.getReviewCount(item.articleId);
        holder.itemReviewCount.setText("(" + reviewCount + ")");
        int avgReview = DatabaseManager.reviews.getAvgRating(item.articleId);
        holder.reviewRatingBar.setNumStars(avgReview);
        holder.itemPrice.setText("$" + item.price);
        holder.itemView.setOnClickListener(e -> {
            Intent itemIntent = new Intent(context, ItemActivity.class);
            itemIntent.putExtra("articleId", item.articleId);
            context.startActivity(itemIntent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder{

    View itemView;
    TextView itemTitle;
    TextView itemReviewCount;
    TextView itemPrice;
    RatingBar reviewRatingBar;


    public ItemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        itemTitle = itemView.findViewById(R.id.listing_title);
        itemReviewCount = itemView.findViewById(R.id.listing_reviews);
        reviewRatingBar = itemView.findViewById(R.id.listing_rating_bar);
        itemPrice = itemView.findViewById(R.id.listing_price);
    }
}

