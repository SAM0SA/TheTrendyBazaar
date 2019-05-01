package com.thetrendybazaar.thetrendybazaar;

import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    static final CustomerManager customers = new CustomerManager();
    static final EmployeeManager employees = new EmployeeManager();
    static final ManufacturerManager manufacturers = new ManufacturerManager();
    static final OrderManager orders = new OrderManager();
    static final ItemManager items = new ItemManager();
    static final PaymentInfoManager paymentInfos = new PaymentInfoManager();
    static final ShoppingCartManager shoppingCarts = new ShoppingCartManager();
    static final ReturnManager returns = new ReturnManager();
    static final ShipmentManager shipments = new ShipmentManager();
    static final ReviewManager reviews = new ReviewManager();

    public static void setDb(SQLiteDatabase read, SQLiteDatabase write){
        customers.setDb(read, write);
        employees.setDb(read, write);
        manufacturers.setDb(read, write);
        orders.setDb(read, write);
        items.setDb(read, write);
        paymentInfos.setDb(read, write);
        shoppingCarts.setDb(read, write);
        returns.setDb(read, write);
        shipments.setDb(read, write);
        reviews.setDb(read, write);
    }
}
