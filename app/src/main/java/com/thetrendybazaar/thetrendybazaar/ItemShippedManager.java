package com.thetrendybazaar.thetrendybazaar;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ItemShippedManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "ItemShipped";

    public long add(int articleId, int shipmentId){
        ContentValues vals = new ContentValues();
        vals.put("ShipmentId", shipmentId);
        vals.put("ArticleId", articleId);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(int articleId, int shipmentId){
        writeDb.delete(tableName, "ArticleId= ? AND ShipmentId= ?", new String[] {articleId + "", shipmentId + ""});
    }

    public void update(Item item, int shipmentId){
        ContentValues vals = new ContentValues();
        vals.put("ShipmentId", shipmentId);
        vals.put("ArticleId", item.articleId);
        writeDb.update(tableName, vals, "ArticleId= ? AND ShipmentId= ?", new String[] {item.articleId + "", shipmentId + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }
}
