package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReturnManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Return";

    public int add(Return returnItem){
        ContentValues vals = new ContentValues();
        vals.put("ReturnId", returnItem.returnId);
        vals.put("ItemQuantity", returnItem.itemQuantity);
        vals.put("OrderNumber", returnItem.orderNumber);
        vals.put("ReturnReason", returnItem.returnReason);
        long articleId = writeDb.insert(tableName, null, vals);
        return 0;
    }
    public void delete(Return returnItem){
        writeDb.delete(tableName, "ReturnId=" + returnItem.returnId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public Return select(int returnId){
        Cursor cursor = readDb.query(tableName, null, "ReturnId= ?", new String[]{returnId + ""}, null, null, null, null);
        Return r = null;
        if(cursor != null){
            cursor.moveToFirst();
            r = new Return(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            );
        }
        return r;
    }
}
