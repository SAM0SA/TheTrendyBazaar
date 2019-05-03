package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.List;

public class ItemDashboardActivity extends AppCompatActivity {

    RecyclerView dashBoardItemRecycler;
    Button logoutBtn, ordersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_dashboard);

        dashBoardItemRecycler = findViewById(R.id.itemsRecycler);
        logoutBtn = findViewById(R.id.items_logout);
        ordersBtn = findViewById(R.id.dashboardOrdersBtn);

        //List<Item> items = DatabaseManager.items.getItems();
        CartItemAdapter adapter = new CartItemAdapter(this, null , true);
        dashBoardItemRecycler.setAdapter(adapter);
        dashBoardItemRecycler.setLayoutManager(new LinearLayoutManager(this));

        logoutBtn.setOnClickListener(e -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        ordersBtn.setOnClickListener(e -> {
            onBackPressed();
        });


    }
}
