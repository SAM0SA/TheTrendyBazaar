package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Camera;
import android.support.annotation.Nullable;
import android.util.Log;

public class EcommerceDatabaseHelper extends SQLiteOpenHelper {

    public static int customerId = 0;
    public static final String dbName = "Ecommerce";

    public EcommerceDatabaseHelper(Context c){
        super(c, dbName, null, 1);

    }

    SQLiteDatabase getReadDb(){
        return this.getReadableDatabase();
    }

    SQLiteDatabase getWriteDb(){
        return this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + " Customer" +
                "( CustomerId INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "FirstName VARCHAR(20)," +
                "LastName VARCHAR(20)," +
                "EmailAddress VARCHAR(50)," +
                "Password VARCHAR(50)," +
                "Address VARCHAR(200)," +
                "PhoneNumber INT )"
                );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
