package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ItemManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Item";

    public long add(Item item){
        ContentValues vals = new ContentValues();
        vals.put("ArticleId", item.articleId);
        vals.put("ManufacturerId", item.manufacturerId);
        vals.put("Quantity", item.quantity);
        vals.put("Price", item.price);
        vals.put("Description", item.description);
        vals.put("Category", item.category);
        vals.put("ItemName", item.name);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Item item){
        writeDb.delete(tableName, "ArticleId=" + item.articleId, null);
    }

    public void update(Item item){
        ContentValues vals = new ContentValues();
        vals.put("ArticleId", item.articleId);
        vals.put("ManufacturerId", item.manufacturerId);
        vals.put("Quantity", item.quantity);
        vals.put("Price", item.price);
        vals.put("Description", item.description);
        vals.put("Category", item.category);
        vals.put("ItemName", item.name);
        writeDb.update(tableName, vals, "ArticleId=" + item.articleId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }


    public ArrayList<Item> getItems(){
        Cursor cursor = readDb.query(tableName, null, null, null, null, null, null, null);
        ArrayList<Item> items = new ArrayList<>();
        Item i;
        if(cursor != null){
            cursor.moveToFirst();
            do{
                i = new Item(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                items.add(i);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return items;
    }
    public ArrayList<Item> getItemsByCategory(String category){
        Cursor cursor = readDb.query(tableName, null, "Category = ?", new String[] {category + ""}, null, null, null, null);
        ArrayList<Item> categoryItems = new ArrayList<>();
        if(cursor != null){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(0));
                categoryItems.add(i);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return categoryItems;
    }

    public int numOfItemsInCategory(String category){
        Cursor cursor = readDb.rawQuery("SELECT COUNT(*) FROM " + tableName + " WHERE Category = " + category, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int getItemCount(){
        Cursor cursor = readDb.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
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
