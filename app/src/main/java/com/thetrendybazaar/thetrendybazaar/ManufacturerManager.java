package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ManufacturerManager {

    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Manufacturer";

    public int add(Manufacturer manufacturer){
        ContentValues vals = new ContentValues();
        vals.put("ManufacturerId", manufacturer.manufacturerId);
        vals.put("Name", manufacturer.name);
        vals.put("Address", manufacturer.address);
        vals.put("Phone", manufacturer.phone);
        vals.put("Email", manufacturer.email);
        long manId = writeDb.insert(tableName, null, vals);
        return 0;
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

    public Manufacturer select(int manufacturerId){

//        Cursor cursor = newDb.rawQuery("SELECT * FROM " + dbName + " WHERE CustomerId = ? ", new String[] {customerId + ""}  );
        Cursor cursor = readDb.query(tableName, null, "ManufacturerId = ?", new String[]{manufacturerId + ""}, null, null, null, null);
        Manufacturer m = null;
        if(cursor != null){
            cursor.moveToFirst();
            m = new Manufacturer(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4));

        }
        return m;
    }
}
