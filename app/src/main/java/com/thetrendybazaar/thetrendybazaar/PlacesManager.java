package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class PlacesManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Places";

    public long add(int customerId, int orderNumber){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customerId);
        vals.put("OrderNumber", orderNumber);
        vals.put("Date", Calendar.getInstance().getTime().toString());
        long index = writeDb.insert(tableName, null, vals);
        Order currOrder = DatabaseManager.orders.select(orderNumber);
        adjustForOrder(currOrder);
        DatabaseManager.generatedFrom.add(orderNumber, currOrder.cartId);
        return index;
    }

    public void delete(Order order){
        writeDb.delete(tableName, "OrderNumber= ?", new String[] {order.orderNumber  + ""});
    }

    public void update(Customer customer, Order order, Date date){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customer.id);
        vals.put("OrderNumber", order.orderNumber);
        vals.put("Date", date.toString());
        writeDb.update(tableName, vals, "OrderNumber = ?", new String[] {order.orderNumber + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public void adjustForOrder(Order order){
        ShoppingCart cart = DatabaseManager.currentShoppingCarts.select(order.cartId);
        ArrayList<Item> itemsInOrder = DatabaseManager.contains.getItemsForShoppingCart(cart.cartId);
        for(int j = 0; j<itemsInOrder.size(); j++){
            Item i = itemsInOrder.get(j);
            int cartQuantity = DatabaseManager.contains.getQuantityInCart(cart.cartId, i.articleId);
            int prev = i.quantity + cartQuantity;
            i.quantity = i.quantity;
            DatabaseManager.items.update(i);
            DatabaseManager.updates.add(order, 0, i, null, prev);
        }
    }
    public ArrayList<Order> getOrdersMadeByCustomer(Customer customer){
        Cursor cursor = readDb.query(tableName, null, "CustomerId = ?", new String[]{customer.id + ""}, null, null, null, null);
        ArrayList<Order> ordersMade = new ArrayList<>();
        Order o;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                o = DatabaseManager.orders.select(cursor.getInt(1));
                ordersMade.add(o);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return ordersMade;
    }

    public int getOrderCount(Customer customer){
        Cursor cursor = readDb.rawQuery("SELECT COUNT(*) FROM " + tableName + " WHERE CustomerId = " + customer.id, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int getCustomerId(int orderNumber){
        Cursor cursor = readDb.query(tableName, null, "OrderNumber= ?", new String[]{orderNumber + ""}, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
}
