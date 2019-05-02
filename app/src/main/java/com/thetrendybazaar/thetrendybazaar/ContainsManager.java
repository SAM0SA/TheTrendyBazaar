package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContainsManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Contains";

    public long add(Item item, ShoppingCart shoppingCart){
        ContentValues vals = new ContentValues();
        vals.put("CartId", shoppingCart.cartId);
        vals.put("ArticleId", item.articleId);
        long index = writeDb.insert(tableName, null, vals);
        return index;
    }
    public void delete(Item item, ShoppingCart shoppingCart){
        writeDb.delete(tableName, "CartId= ? AND ArticleId = ?", new String[] {shoppingCart.cartId + "", item.articleId + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Item> getItemsForShoppingCart(int cartId){
        Cursor cursor = readDb.query(tableName, null, "cartId = ?", new String[] {cartId + ""}, null, null, null, null);
        ArrayList<Item> cartItems = new ArrayList<>();
        if(cursor != null){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(1));
                cartItems.add(i);
            }while(cursor.moveToNext());
        }
        return cartItems;
    }
}
