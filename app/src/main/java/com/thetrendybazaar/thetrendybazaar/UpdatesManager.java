package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdatesManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Updates";

    public long add(Order order, Return returnThing, Item item, Employee employee, int prevQuantity){
        ContentValues vals = new ContentValues();
        if(employee!=null){
            vals.put("EmployeeId", employee.employeeId);
            vals.put("Datetime", Calendar.getInstance().getTime().toString());
            vals.put("ArticleId", item.articleId);
            vals.put("PrevQuantity", prevQuantity);
            vals.put("NewQuantity", item.quantity);
            //update item in main
        }
        else if(order!=null){
            ArrayList<Item> items = DatabaseManager.orders.getItemsForOrder(order.orderNumber);
            for(int i = 0; i<items.size(); i++){
                vals.put("OrderNumber", order.orderNumber);
                vals.put("Datetime", Calendar.getInstance().getTime().toString());
                vals.put("ArticleId", items.get(i).articleId);
                vals.put("PrevQuantity", prevQuantity);
                vals.put("NewQuantity", item.quantity);
            }
        }
        else if(returnThing!=null){
            ArrayList<Item> items = DatabaseManager.returns.getItemsForReturn(returnThing.returnId);
            for(int i = 0; i<items.size(); i++){
                vals.put("ReturnId", returnThing.returnId);
                vals.put("Datetime", Calendar.getInstance().getTime().toString());
                vals.put("ArticleId", items.get(i).articleId);
                vals.put("PrevQuantity", prevQuantity);
                vals.put("NewQuantity", item.quantity);
            }
        }
        return writeDb.insert(tableName, null, vals);
    }

    public static void setDb(android.database.sqlite.SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }
}
