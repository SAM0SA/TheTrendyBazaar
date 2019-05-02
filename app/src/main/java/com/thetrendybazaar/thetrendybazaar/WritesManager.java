package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class WritesManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Writes";

    public long add(Customer customer, Review review){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customer.id);
        vals.put("ReviewId", review.id);
        vals.put("Date",  Calendar.getInstance().getTime().toString());
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Customer customer, Review review){
        writeDb.delete(tableName, "CustomerId= ? AND ReviewId= ?", new String[] {customer.id + "", review.id + ""});
    }

    public String getDateOfReview(Review reviewId){
        Cursor cursor = readDb.query(tableName, null, "ReviewId = ?", new String[]{reviewId + ""}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor.getString(2);
    }
    public void update(Customer customer, Review review){
        ContentValues vals = new ContentValues();
        vals.put("CustomerId", customer.id);
        vals.put("ReviewId", review.id);
        writeDb.update(tableName, vals, "CustomerId= ? AND ReviewId= ?", new String[] {customer.id + "", review.id + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Review> getReviews(Customer customer){
        Cursor cursor = readDb.query(tableName, null, "CustomerId = ?", new String[]{customer.id + ""}, null, null, null, null);
        ArrayList<Review> reviewsWritten = new ArrayList<>();
        Review r;
        if(cursor != null){
            cursor.moveToFirst();
            do{
                r = DatabaseManager.reviews.select(cursor.getInt(1));
                reviewsWritten.add(r);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return reviewsWritten;
    }
}
