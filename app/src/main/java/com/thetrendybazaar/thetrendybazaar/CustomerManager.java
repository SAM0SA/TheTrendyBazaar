package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CustomerManager {

    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Customer";

    public long addCustomer(Customer customer){
        ContentValues vals = new ContentValues();
        vals.put("FirstName", customer.firstName);
        vals.put("LastName", customer.lastName);
        vals.put("EmailAddress", customer.email);
        vals.put("Password", customer.password);
        vals.put("Address", customer.address);
        vals.put("PhoneNumber", customer.phone);
        return writeDb.insert(tableName, null, vals);
    }
    public void deleteCustomer(Customer customer){
        writeDb.delete(tableName, "CustomerId=" + customer.id, null);
    }

    public void updateCustomer(Customer customer){
        ContentValues vals = new ContentValues();
        vals.put("FirstName", customer.firstName);
        vals.put("LastName", customer.lastName);
        vals.put("EmailAddress", customer.email);
        vals.put("Password", customer.password);
        vals.put("Address", customer.address);
        vals.put("PhoneNumber", customer.phone);
        writeDb.update(tableName, vals, "CustomerId=" + customer.id, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public Customer select(int customerId){
        Cursor cursor = readDb.query(tableName, null, "CustomerId = ?", new String[]{customerId + ""}, null, null, null, null);
        Customer c = null;
        if(cursor != null){
            cursor.moveToFirst();
            c = new Customer(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
        }
        return c;
    }


}
