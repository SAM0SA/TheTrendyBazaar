package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OrderManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Order";

    public int add(Order order){
        ContentValues vals = new ContentValues();
        vals.put("OrderNumber", order.orderNumber);
        vals.put("CartId", order.cartId);
        vals.put("CardNumber", order.cardNumber);
        long articleId = writeDb.insert(tableName, null, vals);
        return 0;
    }
    public void delete(Order order){
        writeDb.delete(tableName, "OrderNumber=" + order.orderNumber, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
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
        }
        return o;
    }
}
