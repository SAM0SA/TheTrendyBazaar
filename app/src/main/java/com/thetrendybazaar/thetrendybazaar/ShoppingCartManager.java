package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class ShoppingCartManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "ShoppingCart";

    public int add(ShoppingCart shoppingCart){
        ContentValues vals = new ContentValues();
        vals.put("CartId", shoppingCart.cartId);
        vals.put("TotalPrice", shoppingCart.totalPrice);
        vals.put("ItemQuantity", shoppingCart.itemQuantity);
        vals.put("CustomerId", shoppingCart.customerId);
        long index = writeDb.insert(tableName, null, vals);
        return 0;
    }
    public void delete(ShoppingCart shoppingCart){
        writeDb.delete(tableName, "CartId=" + shoppingCart.cartId, null);
    }

    public void update(ShoppingCart shoppingCart){
        ContentValues vals = new ContentValues();
        vals.put("CartId", shoppingCart.cartId);
        vals.put("TotalPrice", shoppingCart.totalPrice);
        vals.put("ItemQuantity", shoppingCart.itemQuantity);
        vals.put("CustomerId", shoppingCart.customerId);
        writeDb.update(tableName, vals, "CartId=" + shoppingCart.cartId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ShoppingCart select(int cartId){
        Cursor cursor = readDb.query(tableName, null, "CartId = ?", new String[]{cartId + ""}, null, null, null, null);
        ShoppingCart s = null;
        if(cursor != null){
            cursor.moveToFirst();
            s = new ShoppingCart(
                    cursor.getInt(0),
                    cursor.getDouble(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
        }
        return s;
    }
}
