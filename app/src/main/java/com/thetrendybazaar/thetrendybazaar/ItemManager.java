package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Item";

    public int add(Item item){
        ContentValues vals = new ContentValues();
        vals.put("ArticleId", item.articleId);
        vals.put("ManufacturerId", item.manufacturerId);
        vals.put("Quantity", item.quantity);
        vals.put("Price", item.price);
        vals.put("Description", item.description);
        vals.put("Category", item.category);
        vals.put("ItemName", item.name);
        long articleId = writeDb.insert(tableName, null, vals);
        return 0;
    }
    public void delete(Item item){
        writeDb.delete(tableName, "ArticleId=" + item.articleId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public Item select(int articleId){
        Cursor cursor = readDb.query(tableName, null, "ArticleId = ?", new String[]{articleId + ""}, null, null, null, null);
        Item i = null;
        if(cursor != null){
            cursor.moveToFirst();
            i = new Item(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }
        return i;
    }
}
