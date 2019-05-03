package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class ShipmentManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Shipment";

    public long add(Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("Address", shipment.address);
        vals.put("ShipmentId", shipment.shipmentId);
        vals.put("ShippingService", shipment.shippingService);
        vals.put("ShipmentType", shipment.shipmentType);
        vals.put("ShipmentPrice", shipment.shipmentPrice);
        vals.put("OrderNumber", shipment.orderNumber);

        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Shipment shipment){
        writeDb.delete(tableName, "ShipmentId=" + shipment.shipmentId, null);
    }

    public void update(Shipment shipment){
        ContentValues vals = new ContentValues();
        vals.put("Address", shipment.address);
        vals.put("ShipmentId", shipment.shipmentId);
        vals.put("ShippingService", shipment.shippingService);
        vals.put("Shipmenttype", shipment.shipmentType);
        vals.put("ShipmentPrice", shipment.shipmentPrice);
        vals.put("OrderNumber", shipment.orderNumber);
        writeDb.update(tableName, vals, "ShipmentId=" + shipment.shipmentId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public Shipment select(int shipmentId){
        Cursor cursor = readDb.query(tableName, null, "ShipmentId = ?", new String[]{shipmentId + ""}, null, null, null, null);
        Shipment s = null;
        if(cursor != null){
            cursor.moveToFirst();
            s = new Shipment(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
        }
        return s;
    }
}
