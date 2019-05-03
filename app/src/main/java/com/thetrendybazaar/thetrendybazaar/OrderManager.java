package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class OrderManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Orders";

    public long add(Order order){
        ContentValues vals = new ContentValues();
        vals.put("OrderNumber", order.orderNumber);
        vals.put("CartId", order.cartId);
        vals.put("CardNumber", order.cardNumber);
        vals.put("Address", order.address);
        long id = writeDb.insert(tableName, null, vals);
        order.orderNumber = (int) id;
        return id;
    }
    public void delete(Order order){
        writeDb.delete(tableName, "OrderNumber=" + order.orderNumber, null);
    }



    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Item> getItemsForOrder(int orderNumber){
        Cursor cursor = readDb.query(tableName, null, "OrderNumber = ?", new String[] {orderNumber + ""}, null, null, null, null);
        ArrayList<Item> orderedItems = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(0));
                orderedItems.add(i);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return orderedItems;
    }
    public Order select(int orderNumber){
        Cursor cursor = readDb.query(tableName, null, "OrderNumber= ?", new String[]{orderNumber + ""}, null, null, null, null);
        Order o = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            o = new Order(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getLong(2),
                    cursor.getString(3)
            );
            cursor.close();
        }
        return o;
    }

    public ArrayList<Order> getOrders(){
        Cursor cursor = readDb.query(tableName, null, null, null, null, null, null, null);
        ArrayList<Order> orders = new ArrayList<>();
        Order o;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                o = new Order(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getLong(2),
                        cursor.getString(3)
                );
                orders.add(o);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return orders;
    }
}
