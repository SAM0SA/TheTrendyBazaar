package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class SentForManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "SentFor";

    public long add(Order order, Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("OrderNumber", order.orderNumber);
        vals.put("DateShipped", shipment.shipmentId);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Order order, Shipment shipment){
        writeDb.delete(tableName, "OrderNumber= ? AND ShipmentId = ?", new String[] {order.orderNumber + "", shipment.shipmentId + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public void update(Order order, Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("OrderNumber", order.orderNumber);
        vals.put("DateShipped", shipment.shipmentId);
        writeDb.update(tableName, vals, "OrderNumber=" + order.orderNumber + " AND ShipmentId=" + shipment.shipmentId, null);
    }
}
