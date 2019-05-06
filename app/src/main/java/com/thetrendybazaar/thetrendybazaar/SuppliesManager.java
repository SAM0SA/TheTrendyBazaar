package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class SuppliesManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Supplies";

    public long add(Item item, int manufacturerId, double supplierPrice){
        ContentValues vals = new ContentValues();
        vals.put("ManufacturerId", manufacturerId);
        vals.put("ArticleId", item.articleId);
        vals.put("SupplierPrice", supplierPrice);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Item item){
        writeDb.delete(tableName, "ArticleId= ?", new String[] {item.articleId + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public void update(Item item, Manufacturer manufacturer, double supplierPrice){
        ContentValues vals = new ContentValues();
        vals.put("ManufacturerId", manufacturer.manufacturerId);
        vals.put("ArticleId", item.articleId);
        vals.put("SupplierPrice", supplierPrice);
        writeDb.update(tableName, vals, "ArticleId=" + item.articleId, null);
    }

    public Manufacturer getManufacturer(int articleId){
        Cursor cursor = readDb.rawQuery("SELECT M.ManufacturerId FROM " + tableName + " WHERE ArticleId = " + articleId, null);
        cursor.moveToFirst();
        return DatabaseManager.manufacturers.select(cursor.getInt(0));
    }
    public ArrayList<Item> getItemsManufacturedBy(int manufacturerId){
        Cursor cursor = readDb.query(tableName, null, "ManufacturerId = ?", new String[] {manufacturerId + ""}, null, null, null, null);
        ArrayList<Item> itemsMan = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(1));
                itemsMan.add(i);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return itemsMan;
    }
}
