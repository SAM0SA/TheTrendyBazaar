package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class ManufacturersActivity extends AppCompatActivity {

    RecyclerView manuRecycler;
    Button logoutBtn, itemsBtn, orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);

        manuRecycler = findViewById(R.id.manuRecycler);
        OrderAdapter adapter = new OrderAdapter(this, OrderAdapter.MANU);
        manuRecycler.setAdapter(adapter);
        manuRecycler.setLayoutManager(new LinearLayoutManager(this));

        logoutBtn = findViewById(R.id.manu_logout);
        itemsBtn = findViewById(R.id.manuItemBtn);
        orderBtn = findViewById(R.id.manuOrdersBtn);

        orderBtn.setOnClickListener(e -> {
            Intent orderIntent = new Intent(this, DashboardActivity.class);
            startActivity(orderIntent);
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
