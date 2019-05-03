package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShoppingCartActivity extends AppCompatActivity {

    RecyclerView cartItemRecyclerView;
    Button checkoutBtn;
    TextView emptyCartText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        emptyCartText = findViewById(R.id.empty_cart_text);

        cartItemRecyclerView = findViewById(R.id.shopping_cart_recyclerView);
        CartItemAdapter adapter = new CartItemAdapter(this, emptyCartText);
        cartItemRecyclerView.setAdapter(adapter);
        cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutBtn = findViewById(R.id.check_out_button);
        checkoutBtn.setOnClickListener(e -> {
            Intent checkoutIntent = new Intent(this, CheckoutActivity.class);
            startActivity(checkoutIntent);
        });
        if(DatabaseManager.contains
                .getItemsForShoppingCart(ShoppingCart.currentShoppingCardId).size() == 0){
            emptyCartText.setVisibility(View.VISIBLE);
        }
    }
}
