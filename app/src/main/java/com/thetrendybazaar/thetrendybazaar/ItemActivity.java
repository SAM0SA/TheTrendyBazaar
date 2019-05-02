package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

//    Spinner spinner;
//    NumberPicker numberPicker;
//    Recycler Views
    RecyclerView reviewsRecyclerView;

//    TextViews
    TextView itemName, itemPrice, itemDescription, itemReviewCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ViewPager viewPager = findViewById(R.id.item_view_pager);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        viewPager.setAdapter(imageAdapter);

        Intent intent = getIntent();
        int id = intent.getIntExtra("articleId", -1);

        reviewsRecyclerView = findViewById(R.id.reviews_recycler);
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this,
                DatabaseManager.reviews.getReviews(id));
        reviewsRecyclerView.setAdapter(reviewsAdapter);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Item currentItem = DatabaseManager.items.select(id);

        itemName = findViewById(R.id.item_page_name_text);
        itemPrice = findViewById(R.id.item_page_price_text);
        itemDescription = findViewById(R.id.item_description_text);
        itemReviewCount = findViewById(R.id.item_page_avg_review_count);

        itemName.setText(currentItem.name);
        itemPrice.setText("$"+currentItem.price);
        itemDescription.setText(currentItem.description);
        itemDescription.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        itemReviewCount.setText("" + DatabaseManager.reviews.getReviewCount(id));

    }
}
