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
                "\tExpiryDate DATE,\n" +
                "\tPRIMARY KEY (CardNumber)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Orders (\n" +
                "\tOrderNumber INT PRIMARY KEY,\n" +
                "\tCartId INT NOT NULL,\n" +
                "\tCardNumber INT NOT NULL\n" +
                ");\n");


        //TRIGGER TO CHECK IF THERE IS AT LEAST ONE ITEM IN CURRENT SHOPPING CART
        db.execSQL("\tCREATE TRIGGER SomeItemsInCart BEFORE INSERT ON Orders" +
                "\tBEGIN"+
                " SELECT CASE WHEN (SELECT S.ItemQuantity FROM CurrentShoppingCart S" +
                " WHERE S.CartId=" + "NEW.CartId)" +
                "<=0" + "\tTHEN\tRAISE (ABORT, '') END;END;");

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
                "CartId INT PRIMARY KEY,\n" +
                "\tTotalPrice DOUBLE,\n" +
                "\tItemQuantity INT,\n" +
                "\tCustomerId INT,\n" +
                "\tPRIMARY KEY (CartId),\n" +
                "\tFOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE CurrentShoppingCart (\n" +
                "CartId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tTotalPrice DOUBLE,\n" +
                "\tItemQuantity INT,\n" +
                "\tCustomerId INT,\n" +
                "\tFOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Return (\n" +
                "\tReturnId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tItemQuantityReturned INT DEFAULT 1,\n" +
                "\tOrderNumber INT,\n" +
                "\tReturnReason VARCHAR(100),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES Orders(OrderNumber)\n" +
                ");\n");

        db.execSQL("\n" +
                "CREATE TABLE Shipment (\n" +
                "\tShipmentId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tAddress VARCHAR(100),\n" +
                "\tShipmentType VARCHAR(250),\n" +
                "\tShipmentPrice DOUBLE,\n" +
                "\tOrderNumber INT,\n" +
                "ShippingService VARCHAR(50),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES Orders(OrderNumber)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Item(\n" +
                "\tArticleId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tManufacturerId INT,\n" +
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
                "\tShipmentId INT,\n" +
                "\tArticleId INT,\n" +
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

        db.execSQL("CREATE TABLE Writes (\n" +
                "\tCustomerId INT NOT NULL,\n" +
                "\tReviewId INT NOT NULL,\n" +
                "\tDate DATETIME,\n" +
                "\tPRIMARY KEY (ReviewId),\n" +
                "FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),\n" +
                "\tFOREIGN KEY (ReviewId) REFERENCES Reviews(ReviewId)\n" +
                "\t\tON DELETE CASCADE\n" +
                ");\n");

        db.execSQL("CREATE TABLE Adds (\n" +
                "\tCustomerId INT,\n" +
                "\tCardNumber INT,\n" +
                "\tPRIMARY KEY (CustomerId, CardNumber),\n" +
                "\tFOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),\n" +
                "\tFOREIGN KEY (CardNumber) REFERENCES PaymentInfo(CardNumber)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Places (\n" +
                "\tCustomerId INT NOT NULL,\n" +
                "\tOrderNumber INT NOT NULL,\n" +
                "\tDate DATETIME,\n" +
                "\tPRIMARY KEY (OrderNumber),\n" +
                "\tFOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)\n" +
                "\tON DELETE CASCADE," +
                "\tFOREIGN KEY (OrderNumber) REFERENCES Orders(OrderNumber))\n" );

        //TRIGGER TO CHECK THAT PAYMENT METHOD EXISTS
        db.execSQL("\tCREATE TRIGGER PaymentMethodExists BEFORE INSERT ON Places" +
                "\tBEGIN"+
                " SELECT CASE WHEN (SELECT COUNT(*) FROM Adds A" +
                " WHERE A.CustomerId=" + "NEW.CustomerId)" +
                "<=0" + "\tTHEN\tRAISE (ABORT, '') END;END;");

        db.execSQL("CREATE TABLE Images (\n" +
                "\tArticleId INT,\n" +
                "\turl VARCHAR(200),\n" +
                "\tPRIMARY KEY (ArticleId, url),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item(ArticleId)\n" +
                "\tON DELETE CASCADE" +
                ");\n");

        db.execSQL("\tCREATE TRIGGER PaymentMethodExists BEFORE INSERT ON Places" +
                "\tBEGIN"+
                " SELECT CASE WHEN (SELECT COUNT(*) FROM Adds A" +
                " WHERE A.CustomerId=" + "NEW.CustomerId)" +
                "<=0" + "\tTHEN\tRAISE (ABORT, '') END;END;");

        db.execSQL("CREATE TABLE Supplies (\n" +
                "\tManufacturerId INT,\n" +
                "\tArticleId INT,\n" +
                "\tSupplierPrice DOUBLE,\n" +
                "\tPRIMARY KEY (ArticleId),\n" +
                "\tFOREIGN KEY (ManufacturerId) REFERENCES Manufacturer(ManufacturerId),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item (ArticleId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE SentFor (\n" +
                "\tOrderNumber INT,\n" +
                "\tDateShipped INT,\n" +
                "\tPRIMARY KEY (OrderNumber, DateShipped),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES Orders(OrderNumber)\n" +
                ");\n");

        db.execSQL("\n" +
                "CREATE TABLE ReportsTo (\n" +
                "\tSupervisorId INT,\n" +
                "\tSubordinateId INT,\n" +
                "\tPRIMARY KEY (SupervisorId, SubordinateId),\n" +
                "\tFOREIGN KEY (SupervisorId) REFERENCES Employee (EmployeeId),\n" +
                "\tFOREIGN KEY (SubordinateId) REFERENCES Employee (EmployeeId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Updates (\n" +
                "\tOrderNumber INT,\n" +
                "\tReturnId INT,\n" +
                "\tEmployeeId INT,\n" +
                "\tDatetime DATETIME,\n" +
                "\tArticleId INT,\n" +
                "\tPrevQuantity INT,\n" +
                "\tNewQuantity INT,\n" +
                "\tPRIMARY KEY (ArticleId,Datetime),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item(ArticleId),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES Orders(OrderNumber),\n" +
                "FOREIGN KEY (ReturnId) REFERENCES Return (ReturnId),\n" +
                "FOREIGN KEY (EmployeeId) REFERENCES Employee (EmployeeId)\n" +
                ");\n");

        db.execSQL("CREATE TABLE GeneratedFrom (\n" +
                "\tOrderNumber INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tCartId INT,\n" +
                "\tOrderDate DATETIME,\n" +
                "\tPRIMARY KEY (OrderNumber),\n" +
                "\tFOREIGN KEY (OrderNumber) REFERENCES Orders(OrderNumber),\n" +
                "\tFOREIGN KEY (CartId) REFERENCES ShoppingCart (CartId)\n" +
                ");\n");

        db.execSQL("\n" +
                "CREATE TABLE Contains (\n" +
                "\tCartId INT,\n" +
                "\tArticleId INT,\n" +
                "\tPRIMARY KEY (CartId,ArticleId),\n" +
                "\tFOREIGN KEY (CartId) REFERENCES ShoppingCart (CartId),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item (ArticleId)\n" +
                ");\n");

        db.execSQL("\n" +
                "CREATE TABLE Tags (\n" +
                "\tArticleId INT PRIMARY KEY,\n" +
                "\t Tag VARCHAR(50),\n" +
                "\t GroupNumber INT,\n" +
                "\tPRIMARY KEY (ArticleId,Tag),\n" +
                "\tFOREIGN KEY (ArticleId) REFERENCES Item (ArticleId)\n" +
                ");\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
