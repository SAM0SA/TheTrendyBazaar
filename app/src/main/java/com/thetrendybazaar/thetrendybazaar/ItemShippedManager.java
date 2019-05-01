package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemShippedManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "ItemShipped";

    public int add(Item item, Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("ShipmentId", shipment.shipmentId);
        vals.put("ArticleId", item.articleId);
        long articleId = writeDb.insert(tableName, null, vals);
        return 0;
    }
    public void delete(Item item, Shipment shipment){
        writeDb.delete(tableName, "ArticleId= ? AND ShipmentId= ?", new String[] {item.articleId + "", shipment.shipmentId + ""});
    }

    public void update(Item item, Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("ShipmentId", shipment.shipmentId);
        vals.put("ArticleId", item.articleId);
        writeDb.update(tableName, vals, "ArticleId= ? AND ShipmentId= ?", new String[] {item.articleId + "", shipment.shipmentId + ""});
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
