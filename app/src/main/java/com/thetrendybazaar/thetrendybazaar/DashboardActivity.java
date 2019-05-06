package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView ordersRecycler;
    Button logoutBtn, itemsBtn, manuBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ordersRecycler = findViewById(R.id.ordersRecycler);
        OrderAdapter adapter = new OrderAdapter(this, OrderAdapter.ORDERS);
        ordersRecycler.setAdapter(adapter);
        ordersRecycler.setLayoutManager(new LinearLayoutManager(this));

        logoutBtn = findViewById(R.id.order_logout);
        itemsBtn = findViewById(R.id.dashboardItemsBtn);
        manuBtn = findViewById(R.id.dashboardManuBtn);

        manuBtn.setOnClickListener(e -> {
            Intent manuIntent = new Intent(this, ManufacturersActivity.class);
            startActivity(manuIntent);
        });

        logoutBtn.setOnClickListener(e -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        itemsBtn.setOnClickListener(e -> {
            Intent intent = new Intent(this, ItemDashboardActivity.class);
            startActivity(intent);
        });

    }
}
