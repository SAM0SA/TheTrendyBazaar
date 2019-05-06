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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private NavigationView mNavigationView;
    private MenuItem accountItem;
    List<Item> items;
    ItemAdapter itemAdapter;

//    Toolbar
    private Toolbar toolbar;

//    Layouts
    private DrawerLayout drawerLayout;

//    Views
    private RecyclerView recyclerView;
    private Spinner filterSpinner;
    private Spinner sortSpinner;

    private int orderSet = 0;
    private String filter = "All";

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

//        Intent intent = getIntent();
        Customer customer = DatabaseManager.customers.select(
                Customer.currentCustomerId);

        ShoppingCart shoppingCart = DatabaseManager.currentShoppingCarts
                .selectByCustomer(customer.id);
        if(shoppingCart == null){
            shoppingCart = DatabaseManager.currentShoppingCarts.generateNewCart(customer.id);
        }
        ShoppingCart.currentShoppingCardId = shoppingCart.cartId;

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(e -> {
            switch (e.getItemId()){
                case R.id.drawer_log_out:
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

            }
            return true;
        });
//        accountItem = mNavigationView.getMenu().findItem(R.id.drawer_my_account);
//
//        accountItem.setTitle(customer.firstName);

        filterSpinner = findViewById(R.id.filter_spinner);
        sortSpinner = findViewById(R.id.sort_by_filter);

        String[] filters = {"Men's Apparel", "Woman's Apparel", "Electronics",
                "Arts & Crafts", "Sports", "All"};

        List<String> filtersList = Arrays.asList(filters);

        String[] catagory = {"MensApparel", "WomansApparel", "Electronics",
                "ArtsCrafts", "Sports", "All"};

        String[] sortBy = {"Price Low-Hi", "Price Hi-Low"};

//        for(int i =0; i < 15; i++){
//            Item it = new Item(i+1, null, null, null, null, catagory[i%5],null);
//            DatabaseManager.items.update(it);
//        }

        ArrayAdapter<CharSequence> filterAdapter = new ArrayAdapter<CharSequence>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                filters
        );

        ArrayAdapter<CharSequence> sortAdapter = new ArrayAdapter<CharSequence>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                sortBy
        );

        recyclerView = findViewById(R.id.items_recycler);
        items = DatabaseManager.items.getItems(0);
        itemAdapter = new ItemAdapter(this, items,true);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterSpinner.setAdapter(filterAdapter);
        sortSpinner.setAdapter(sortAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                switch (text){
                    case "Price Low-Hi":
                        if(!filter.equals("All")){
                            itemAdapter.items = DatabaseManager.items.getItemsByCategory(filter, 1);
                        } else {
                            itemAdapter.items = DatabaseManager.items.getItems(1);
                        }
                        orderSet = 1;
                        itemAdapter.notifyDataSetChanged();
                        break;
                    case "Price Hi-Low":
                        if(!filter.equals("All")){
                            itemAdapter.items = DatabaseManager.items.getItemsByCategory(filter, 2);
                        } else {
                            itemAdapter.items = DatabaseManager.items.getItems(2);
                        }
                        orderSet = 2;
                        itemAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                int index = filtersList.indexOf(text);
                updateList(index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            void updateList(int index){
                switch(index){
                    case 0:
                        filter = catagory[0];
                        itemAdapter.notifyDataSetChanged();
                        itemAdapter.items.clear();
                        itemAdapter.items.addAll(DatabaseManager.items.getItemsByCategory(catagory[0],
                                orderSet));
                        break;
                    case 1:
                        filter = catagory[1];
                        itemAdapter.items.clear();
                        itemAdapter.items.addAll(DatabaseManager.items.getItemsByCategory(catagory[1],
                                orderSet));
                        itemAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        filter = catagory[2];
                        itemAdapter.items.clear();
                        itemAdapter.items.addAll(DatabaseManager.items.getItemsByCategory(catagory[2],
                                orderSet));
                        itemAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        filter = catagory[3];
                        itemAdapter.items.clear();
                        itemAdapter.items.addAll(DatabaseManager.items.getItemsByCategory(catagory[3],
                                orderSet));
                        itemAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        filter = catagory[4];
                        itemAdapter.items.clear();
                        itemAdapter.items.addAll(DatabaseManager.items.getItemsByCategory(catagory[4],
                                orderSet));
                        itemAdapter.notifyDataSetChanged();
                        break;
                    case 5:
                        filter = catagory[5];
                        itemAdapter.items.clear();
                        itemAdapter.items.addAll(DatabaseManager.items.getItems(orderSet));
                        itemAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

//            void updateList(int index){
//                switch (index){
//                    case 0:
//                        String s = "BOB";
//                        items = DatabaseManager.items.getItemsByCategory(catagory[0],
//                                orderSet);
//                        itemAdapter.notifyDataSetChanged();
//                        break;
//
//                    case 1:
//                        filter = catagory[1];
//                        items = DatabaseManager.items.getItemsByCategory(catagory[1],
//                                orderSet);
//                        itemAdapter.notifyDataSetChanged();
//                        break;
//
//                    case 2:
//                        filter = catagory[2];
//                        items = DatabaseManager.items.getItemsByCategory(catagory[2],
//                                orderSet);
//                        itemAdapter.notifyDataSetChanged();
//                        //filter = catagory[0];
//                        break;
//
//                    case 3:
//                        items = DatabaseManager.items.getItemsByCategory(catagory[3],
//                                orderSet);
//                        itemAdapter.notifyDataSetChanged();
//                        filter = catagory[3];
//                        break;
//
//                    case 4:
//                        items = DatabaseManager.items.getItemsByCategory(catagory[4],
//                                orderSet);
//                        itemAdapter.notifyDataSetChanged();
//                        filter = catagory[4];
//                        break;
//                    case 5:
//                        items = DatabaseManager.items.getItems(orderSet);
//                        itemAdapter.notifyDataSetChanged();
//                        filter = "All";
//                        break;
//                default:
//                    String s3 = "BOB";
//                    int i2 = s3.charAt(1);
//                    break;
//                }
//            }
        });







    }


    @Override
    protected void onStart() {
        items = DatabaseManager.items.getItems(0);
        itemAdapter.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else this.moveTaskToBack(true);
    }
}
