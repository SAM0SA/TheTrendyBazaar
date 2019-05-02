package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private NavigationView mNavigationView;
    private MenuItem accountItem;

//    Toolbar
    private Toolbar toolbar;

//    Layouts
    private DrawerLayout drawerLayout;

//    Views
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(null,drawerLayout, toolbar,
                R.string.drawer_open,R.string.drawer_close );
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        accountItem = mNavigationView.getMenu().findItem(R.id.drawer_my_account);
        Intent intent = getIntent();
        accountItem.setTitle(intent.getStringExtra("name"));

        recyclerView = findViewById(R.id.items_recycler);
        List<Item> items = DatabaseManager.items.getItems();
        ItemAdapter itemAdapter = new ItemAdapter(this, items);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }
}
