package com.thetrendybazaar.thetrendybazaar;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AddsManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Adds";

    public int add(Customer customer, PaymentInfo paymentInfo){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customer.id);
        vals.put("CardNumber", paymentInfo.cardNumber);
        writeDb.insert(tableName, null, vals);
        return 0;
    }
    public void delete(Customer customer, PaymentInfo paymentInfo){
        writeDb.delete(tableName, "CustomerId= ? AND CardNumber= ?", new String[] {customer.id + "", paymentInfo.cardNumber + ""});
    }

    public void update(Customer customer, PaymentInfo paymentInfo){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customer.id);
        vals.put("CardNumber", paymentInfo.cardNumber);
        writeDb.update(tableName, vals, "CustomerId= ? AND CardNumber= ?", new String[] {customer.id + "", paymentInfo.cardNumber + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }


    public void select(int articleId, int shipmentId){
//        Cursor cursor = readDb.query(tableName, null, "ArticleId = ? AND ShipmentId = ?", new String[]{articleId + "", shipmentId + ""}, null, null, null, null);
//         i = null;
//        if(cursor != null){
//            cursor.moveToFirst();
//            i = new Item(
//                    cursor.getInt(0),
//                    cursor.getInt(1),
//                    cursor.getInt(2),
//                    cursor.getDouble(3),
//                    cursor.getString(4),
//                    cursor.getString(5),
//                    cursor.getString(6)
//            );
//        }
//        return i;
    }
}
