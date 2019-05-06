package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class SentForShipmentManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "SentForShipment";

    public long add(Order order, String shippingAddress){
        ContentValues vals = new ContentValues();
        vals.put("OrderNumber", order.orderNumber);
        vals.put("DateShipped", Calendar.getInstance().getTime().toString());
        vals.put("Address", shippingAddress);
        long id = writeDb.insert(tableName, null, vals);
        ArrayList<Item> itemsShipped = DatabaseManager.orders.getItemsForOrder(order.orderNumber);
        for(int i = 0; i<itemsShipped.size(); i++){
            Item currItem = itemsShipped.get(i);
            DatabaseManager.itemsShipped.add(currItem.articleId, (int)id);
        }
        return id;
    }
    public void delete(int shipmentId){
        writeDb.delete(tableName, "ShipmentId = ?", new String[] {shipmentId + ""});
    }

    public int checkShipped(int orderNumber){
        Cursor cursor = readDb.query(tableName, null, "OrderNumber= ?", new String[]{orderNumber + ""}, null, null, null, null);
        if(cursor!=null && cursor.getCount()>0){
            return 1;
        }
        return 0;
    }
    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public void update(int shipmentId, String address){
        ContentValues vals = new ContentValues();
        vals.put("Address", address);
        writeDb.update(tableName, vals, "ShipmentId=" + shipmentId, null);
    }
}