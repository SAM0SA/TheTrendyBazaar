package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class StoreActivity extends AppCompatActivity {

    private NavigationView mNavigationView;
    private MenuItem accountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        mNavigationView = findViewById(R.id.nav_view);
        accountItem = mNavigationView.getMenu().findItem(R.id.drawer_my_account);
        Intent intent = getIntent();
        accountItem.setTitle(intent.getStringExtra("name"));
    }
}
