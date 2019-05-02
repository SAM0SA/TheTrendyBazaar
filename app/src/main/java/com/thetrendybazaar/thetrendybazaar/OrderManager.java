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
        return writeDb.insert(tableName, null, vals);
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
        if(cursor != null){
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
        if(cursor != null){
            cursor.moveToFirst();
            o = new Order(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
            cursor.close();
        }
        return o;
    }
}
