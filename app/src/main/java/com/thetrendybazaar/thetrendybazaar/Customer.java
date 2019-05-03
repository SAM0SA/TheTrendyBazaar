package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Customer {

    Integer id;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;
    String password;
    static int currentCustomerId = -1;


    public Customer(Integer id, String firstName, String lastName, String email, String password, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }

    @Override
    public String toString() {
        return "id = " + id + "firstName = " +firstName;
    }
}

