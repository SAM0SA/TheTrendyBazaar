package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Date;

public class PaymentInfoManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "PaymentInfo";

    public long add(PaymentInfo paymentInfo){
        ContentValues vals = new ContentValues();
        vals.put("CardNumber", paymentInfo.cardNumber);
        vals.put("Type", paymentInfo.type);
        vals.put("SecurityCode", paymentInfo.securityCode);
        vals.put("ExpiryDate", paymentInfo.expiryDate + "");
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(PaymentInfo paymentInfo){
        writeDb.delete(tableName, "CardNumber=" + paymentInfo.cardNumber, null);
    }

    public void update(PaymentInfo paymentInfo){
        ContentValues vals = new ContentValues();
        vals.put("CardNumber", paymentInfo.cardNumber);
        vals.put("Type", paymentInfo.type);
        vals.put("SecurityCode", paymentInfo.securityCode);
        vals.put("ExpiryDate", paymentInfo.expiryDate + "");
        writeDb.update(tableName, vals, "CardNumber=" + paymentInfo.cardNumber, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public PaymentInfo select(int cardNumber){
        Cursor cursor = readDb.query(tableName, null, "CardNumber = ?", new String[]{cardNumber + ""}, null, null, null, null);
        PaymentInfo p = null;
        if(cursor != null){
            cursor.moveToFirst();
            p = new PaymentInfo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    new Date(cursor.getLong(3))
            );
            cursor.close();
        }
        return p;
    }
}
