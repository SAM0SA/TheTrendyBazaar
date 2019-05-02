package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Camera;
import android.support.annotation.Nullable;
import android.util.Log;

public class EcommerceDatabaseHelper extends SQLiteOpenHelper {

    public static int customerId = 0;
    public static final String dbName = "Ecommerce";

    public EcommerceDatabaseHelper(Context c){
        super(c, dbName, null, 1);

    }

    SQLiteDatabase getReadDb(){
        return this.getReadableDatabase();
    }

    SQLiteDatabase getWriteDb(){
        return this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + " Customer" +
                "( CustomerId INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "FirstName VARCHAR(20)," +
                "LastName VARCHAR(20)," +
                "EmailAddress VARCHAR(50)," +
                "Password VARCHAR(50)," +
                "Address VARCHAR(200)," +
                "PhoneNumber INT )"
                );
        db.execSQL("CREATE TABLE Review (" +
                "ReviewId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ArticleId INT NOT NULL," +
                "Rating INT DEFAULT NULL," +
                "DetailedReview VARCHAR(500) DEFAULT ''," +
                "FOREIGN KEY (ArticleId) REFERENCES Item(ArticleId)" +
                ");");

        db.execSQL("CREATE TABLE PaymentInfo (\n" +
                "\tCardNumber INT NOT NULL,\n" +
                "\tType VARCHAR(50),\n" +
                "\tSecurityCode INT,\n" +
                "\tExpiryDate DATETIME,\n" +
                "\tPRIMARY KEY (CardNumber)\n" +
                ");\n");
        db.execSQL("CREATE TABLE OrderItem (\n" +
                "\tOrderNumber INT NOT NULL,\n" +
                "\tCartId INT NOT NULL,\n" +
                "\tCardNumber INT NOT NULL,\n" +
                "\tPRIMARY KEY (OrderNumber)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Employee (\n" +
                "\tEmployeeId INT,\n" +
                "\tPosition VARCHAR(100),\n" +
                "\tDateJoined DATETIME,\n" +
                "\tSupervisorId INT,\n" +
                "\tFirstName VARCHAR(20),\n" +
                "\tLastName VARCHAR(20),\n" +
                "\tPRIMARY KEY (EmployeeId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE ShoppingCart (\n" +
                "CartId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tTotalPrice INT,\n" +
                "\tItemQuantity INT,\n" +
                "\tCustomerId INT,\n" +
                "\tFOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Return (\n" +
                "\tReturnId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tItemQuantityReturned INT DEFAULT 1,\n" +
                "\tOrderNumber INT,\n" +
                "\tReturnReason VARCHAR(100),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES OrderItem(OrderNumber)\n" +
                ");\n");

        db.execSQL("\n" +
                "CREATE TABLE Shipment (\n" +
                "\tShipmentId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tAddress VARCHAR(100),\n" +
                "\tShipmentType VARCHAR(250),\n" +
                "\tShipmentPrice VARCHAR(30),\n" +
                "\tOrderNumber INT,\n" +
                "ShippingService VARCHAR(50),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES OrderItem(OrderNumber)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Item(\n" +
                "\tArticleId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tManufacturerId VARCHAR(100),\n" +
                "\tQuantity INT,\n" +
                "\tPrice DOUBLE,\n" +
                "\tDescription VARCHAR(250),\n" +
                "\tCategory VARCHAR(30),\n" +
                "\tItemName VARCHAR(100),\n" +
                "\tFOREIGN KEY (ManufacturerId) REFERENCES Manufacturer (ManufacturerId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Manufacturer (\n" +
                "\tManufacturerId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tName VARCHAR(100),\n" +
                "\tAddress VARCHAR(100),\n" +
                "\tPhone INT,\n" +
                "\tEmail VARCHAR(100)\n" +
                ");\n");


        db.execSQL("CREATE TABLE ItemShipped (\n" +
                "\tShipmentId VARCHAR(100),\n" +
                "\tArticleId VARCHAR(50),\n" +
                "\tPRIMARY KEY (ShipmentId,ArticleId),\n" +
                "\tFOREIGN KEY (ShipmentId) REFERENCES Shipment (ShipmentId),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item (ArticleId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE ItemReturned (\n" +
                "\tReturnId INT,\n" +
                "\tArticleId INT, \n" +
                "\tPRIMARY KEY (ReturnId, ArticleId),\n" +
                "\tFOREIGN KEY (ReturnId) REFERENCES Return(ReturnId),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item (ArticleId)\n" +
                ");\n");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
