package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class CurrentShoppingCartManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "CurrentShoppingCart";

    public long add(ShoppingCart shoppingCart){
        ContentValues vals = new ContentValues();
        vals.put("CartId", shoppingCart.cartId);
        vals.put("TotalPrice", shoppingCart.totalPrice);
        vals.put("ItemQuantity", shoppingCart.itemQuantity);
        vals.put("CustomerId", shoppingCart.customerId);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(ShoppingCart shoppingCart){
        DatabaseManager.shoppingCarts.add(this.select(shoppingCart.cartId));
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
            cursor.close();
        }
        return s;
    }

    public ShoppingCart selectByCustomer(int customerId){
        Cursor cursor = readDb.query(tableName, null, "CustomerId = ?", new String[]{customerId + ""}, null, null, null, null);
        ShoppingCart s = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            s = new ShoppingCart(
                    cursor.getInt(0),
                    cursor.getDouble(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );
            cursor.close();
        }
        return s;
    }

    public ShoppingCart generateNewCart(int customerId){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customerId);
        vals.put("TotalPrice", 0.0);
        vals.put("ItemQuantity", 0);
        long id = writeDb.insert(tableName, null, vals);
        return new ShoppingCart((int)id, 0.0, 0, customerId);
    }

    public void updateCartOnAdd(int articleId, int cartId){
        ShoppingCart currCart = this.select(cartId);
        currCart.updatePrice(DatabaseManager.items.select(articleId).price);
        currCart.updateQuantity(1);
        this.update(currCart);
    }

    public void updateCartOnDelete(int articleId, int cartId, int quantity){
        ShoppingCart currCart = this.select(cartId);
        currCart.updatePrice(DatabaseManager.items.select(articleId).price*quantity*-1);
        currCart.updateQuantity(quantity*-1);
        this.update(currCart);
    }
}
