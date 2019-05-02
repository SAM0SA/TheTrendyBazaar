package com.thetrendybazaar.thetrendybazaar;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ItemShippedManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "ItemShipped";

    public long add(Item item, Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("ShipmentId", shipment.shipmentId);
        vals.put("ArticleId", item.articleId);
        return writeDb.insert(tableName, null, vals);
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
}
