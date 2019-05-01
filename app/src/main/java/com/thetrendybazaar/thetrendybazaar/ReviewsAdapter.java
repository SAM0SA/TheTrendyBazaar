package com.thetrendybazaar.thetrendybazaar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    List<Review> reviews;
    LayoutInflater inflater;

    ReviewsAdapter(Context context, List<Review> reviews){
        this.reviews = reviews;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_layout, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}

class ReviewViewHolder extends RecyclerView.ViewHolder{

    public ReviewViewHolder(View itemView) {
        super(itemView);
    }
}