package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ImagesManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Images";

    public long add(Item item, String url){
        ContentValues vals = new ContentValues();
        vals.put("ArticleId", item.articleId);
        vals.put("url", url);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Item item, String url){
        writeDb.delete(tableName, "ArticleId= ? AND url= ?", new String[] {item.articleId + "", url + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<String> getImages(int articleId){
        Cursor cursor = readDb.query(tableName, null, "ArticleId = ?", new String[] {articleId + ""}, null, null, null, null);
        ArrayList<String> images = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                images.add(cursor.getString(1));
            }while(cursor.moveToNext());
            cursor.close();
        }
        return images;
    }
}
