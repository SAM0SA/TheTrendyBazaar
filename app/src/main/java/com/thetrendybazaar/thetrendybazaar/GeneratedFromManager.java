package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class GeneratedFromManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "GeneratedFrom";

    public long add(int orderNumber, int cartId){
        ContentValues vals = new ContentValues();
        vals.put("OrderNumber", orderNumber);
        vals.put("CartId", cartId);
        vals.put("OrderDate", Calendar.getInstance().getTime().toString());
        return writeDb.insert(tableName, null, vals);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

}
