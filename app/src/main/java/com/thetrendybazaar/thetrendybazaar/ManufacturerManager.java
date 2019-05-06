package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManufacturerManager {

    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Manufacturer";

    public long add(Manufacturer manufacturer){
        ContentValues vals = new ContentValues();
        vals.put("ManufacturerId", manufacturer.manufacturerId);
        vals.put("Name", manufacturer.name);
        vals.put("Address", manufacturer.address);
        vals.put("Phone", manufacturer.phone);
        vals.put("Email", manufacturer.email);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Manufacturer manufacturer){
        writeDb.delete(tableName, "ManugacturerId=" + manufacturer.manufacturerId, null);
    }

    public void update(Manufacturer manufacturer){
        ContentValues vals = new ContentValues();
        vals.put("ManufacturerId", manufacturer.manufacturerId);
        vals.put("Name", manufacturer.name);
        vals.put("Address", manufacturer.address);
        vals.put("Phone", manufacturer.phone);
        vals.put("Email", manufacturer.email);
        writeDb.update(tableName, vals, "ManufacturerId=" + manufacturer.manufacturerId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Manufacturer> getAllManufacturers(){
        Cursor cursor = writeDb.rawQuery("SELECT * FROM " + tableName, null);
        ArrayList<Manufacturer> allManufacturers = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            Manufacturer m;
            cursor.moveToFirst();
            do{
                m = DatabaseManager.manufacturers.select(cursor.getInt(0));
                allManufacturers.add(m);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return allManufacturers;
    }

    public Manufacturer select(int manufacturerId){
        Cursor cursor = readDb.query(tableName, null, "ManufacturerId = ?", new String[]{manufacturerId + ""}, null, null, null, null);
        Manufacturer m = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            m = new Manufacturer(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4));
            cursor.close();
        }
        return m;
    }
}
