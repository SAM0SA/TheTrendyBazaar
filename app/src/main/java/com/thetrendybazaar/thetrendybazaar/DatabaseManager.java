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
    static final CurrentShoppingCartManager currentShoppingCarts = new CurrentShoppingCartManager();
    static final ReturnManager returns = new ReturnManager();
    static final ShipmentManager shipments = new ShipmentManager();
    static final ReviewManager reviews = new ReviewManager();
    static final WritesManager writes = new WritesManager();
    static final AddsManager adds = new AddsManager();
    static final PlacesManager places = new PlacesManager();
    static final ImagesManager images = new ImagesManager();
    static final SuppliesManager supplies = new SuppliesManager();
    static final ReportsToManager reportsTo = new ReportsToManager();
    static final UpdatesManager updates = new UpdatesManager();
    static final SentForManager sentFor = new SentForManager();
    static final ContainsManager contains = new ContainsManager();
    static final GeneratedFromManager generatedFrom = new GeneratedFromManager();
    static final TagsManager tags = new TagsManager();


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
        writes.setDb(read, write);
        adds.setDb(read, write);
        places.setDb(read, write);
        images.setDb(read, write);
        supplies.setDb(read, write);
        reportsTo.setDb(read, write);
        updates.setDb(read, write);
        sentFor.setDb(read, write);
        contains.setDb(read, write);
        generatedFrom.setDb(read, write);
        currentShoppingCarts.setDb(read, write);
        tags.setDb(read, write);
    }
}
