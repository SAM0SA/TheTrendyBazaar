package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemActivity extends AppCompatActivity {

//    Spinner spinner;
//    NumberPicker numberPicker;
//    Recycler Views
    RecyclerView reviewsRecyclerView;

//    RatingBar
    RatingBar ratingBar;
    RatingBar submitReviwRatingBar;

//    TextViews
    TextView itemName, itemPrice, itemDescription, itemReviewCount;

//    Buttons
    Button reviewSubmitBtn;
    Button addToCartBtn;

//    EditTexts
    EditText detailedReviewBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Toolbar toolbar = findViewById(R.id.item_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra("articleId", -1);

        ViewPager viewPager = findViewById(R.id.item_view_pager);
        ImageAdapter imageAdapter = new ImageAdapter(this, id);
        viewPager.setAdapter(imageAdapter);



        reviewsRecyclerView = findViewById(R.id.reviews_recycler);
        List<Review> reviews = DatabaseManager.reviews.getReviews(id);
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this,
                reviews);
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

        ratingBar = findViewById(R.id.ratingBar_avg);
        ratingBar.setRating(DatabaseManager.reviews.getAvgRating(id));

        reviewSubmitBtn = findViewById(R.id.review_submit_btn);
        detailedReviewBody = findViewById(R.id.review_box);
        submitReviwRatingBar = findViewById(R.id.submitReviewRating);

        reviewSubmitBtn.setOnClickListener(e -> {
            Review review = new Review(null,id, (int) submitReviwRatingBar.getRating(),
                    detailedReviewBody.getText().toString());
            DatabaseManager.reviews.add(review);
            DatabaseManager.writes.add(id, review);
            reviews.clear();
            reviews.addAll(DatabaseManager.reviews.getReviews(id));
            reviewsAdapter.notifyDataSetChanged();
            detailedReviewBody.setText("");
            submitReviwRatingBar.setRating(0);
            ratingBar.setRating(DatabaseManager.reviews.getAvgRating(id));
            itemReviewCount.setText("(" + DatabaseManager.reviews.getReviewCount(id) + ")");
        });

        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        addToCartBtn.setOnClickListener(e -> {
            int quantityOfItem = DatabaseManager.items.select(currentItem.articleId).quantity;
            if(quantityOfItem==0){
                Toast.makeText(this, "Item sold out!",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                //decrease item quantity
                DatabaseManager.items.alterQuantity(currentItem.articleId, -1);
                DatabaseManager.contains.add(currentItem.articleId, ShoppingCart.currentShoppingCardId);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.action_cart:
                goToCartActivity();
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToCartActivity() {
        Intent cartIntent = new Intent(this, ShoppingCartActivity.class);
        startActivity(cartIntent);
    }
}
