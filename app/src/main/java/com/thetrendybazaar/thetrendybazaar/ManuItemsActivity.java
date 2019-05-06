package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ManuItemsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_items);

        Intent intent = getIntent();

        recyclerView = findViewById(R.id.manu_items_recycler);
        ItemAdapter adapter = new ItemAdapter(this, DatabaseManager
                .supplies.getItemsManufacturedBy(intent.getIntExtra("manuId",-1)),
                false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
