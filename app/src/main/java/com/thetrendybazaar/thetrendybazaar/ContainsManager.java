package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContainsManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Contains";

    public long add(int itemId, int shoppingCartId){
        ContentValues vals = new ContentValues();
        int q = getQuantityInCart(shoppingCartId, itemId);
        if(q==0){
            vals.put("CartId", shoppingCartId);
            vals.put("ArticleId", itemId);
            vals.put("Quantity", 1);
        }
        else{
            updateQuantity(itemId, shoppingCartId, q, 1);
        }
        DatabaseManager.currentShoppingCarts.updateCartOnAdd(itemId, shoppingCartId);
        long index = writeDb.insert(tableName, null, vals);
        return index;
    }
    public void delete(int itemId, int shoppingCartId){
        int quantity = getQuantityInCart(shoppingCartId, itemId);
        writeDb.delete(tableName, "CartId= ? AND ArticleId = ?", new String[] {shoppingCartId + "", itemId + ""});
        DatabaseManager.currentShoppingCarts.updateCartOnDelete(itemId, shoppingCartId, quantity);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Item> getItemsForShoppingCart(int cartId){
        Cursor cursor = readDb.query(tableName, null, "cartId = ?", new String[] {cartId + ""}, null, null, null, null);
        ArrayList<Item> cartItems = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(1));
                cartItems.add(i);
            }while(cursor.moveToNext());
        }
        return cartItems;
    }

    public void updateQuantity(int articleId, int cartId, int prevQuantity, int addQuantity){
        ContentValues vals = new ContentValues();
        vals.put("Quantity", prevQuantity+addQuantity);
        writeDb.update(tableName, vals, "CartId=" + cartId + " AND ArticleId=" + articleId, null);
    }
    public int getQuantityInCart(int cartId,int articleId){
        Cursor cursor = readDb.query(tableName, null, "CartId = ? AND ArticleId = ?", new String[]{cartId + "", articleId + ""}, null, null, null, null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            int n = cursor.getInt(2);
            return n;
        }
        return 0;
    }
}
