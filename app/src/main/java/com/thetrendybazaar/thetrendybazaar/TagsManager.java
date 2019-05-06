package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TagsManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Tags";

    public long add(int articleId, String tag, int groupNumber){
        ContentValues vals = new ContentValues();
        vals.put("ArticleId", articleId);
        vals.put("Tag", tag);
        vals.put("GroupNumber", groupNumber);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(int articleId, String tag){
        writeDb.delete(tableName, "ArticleId=? AND Tag=?", new String[]{articleId + "", tag});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Item> selectItemsByTag(String tag) {
        Cursor cursor = readDb.query(tableName, null, "Tag = ?", new String[] {tag + ""}, null, null, null, null);
        ArrayList<Item> tagItems = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(0));
                tagItems.add(i);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return tagItems;
    }

    public ArrayList<Item> selectItemsByGroupNumber(int groupNumber){
        Cursor cursor = readDb.query(tableName, null, "GroupNumber = ?", new String[] {groupNumber + ""}, null, null, null, null);
        ArrayList<Item> groupItems = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            Item i;
            cursor.moveToFirst();
            do{
                i = DatabaseManager.items.select(cursor.getInt(0));
                groupItems.add(i);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return groupItems;
    }
}
