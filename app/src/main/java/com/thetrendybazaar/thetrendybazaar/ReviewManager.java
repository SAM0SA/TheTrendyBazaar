package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ReviewManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Review";


    public long add(Review review){
        ContentValues vals = new ContentValues();
        vals.put("ArticleId", review.articleId);
        vals.put("Rating", review.rating);
        vals.put("DetailedReview", review.detailedReview);
        long id = writeDb.insert(tableName, null, vals);
        review.id = (int)id;
        return id;
    }

    public void delete(Review review){
        writeDb.delete(tableName, "ReviewId=" + review.id, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Review> getReviews(int articleId){
        Cursor cursor = readDb.query(tableName, null, "ArticleId = ?", new String[] {articleId + ""}, null, null, null, null);
        ArrayList<Review> reviews = new ArrayList<>();
        Review r;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                r = new Review(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3)
                );
                reviews.add(r);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return reviews;
    }

    public int getReviewCount(int articleId){
        Cursor cursor = readDb.rawQuery("SELECT COUNT(*) FROM " + tableName + " WHERE ArticleId = " + articleId, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int getAvgRating(int articleId){
        Cursor cursor = readDb.rawQuery("SELECT AVG(Rating) FROM " + tableName + " WHERE ArticleId = " + articleId, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public Review select(int reviewId){
        Cursor cursor = readDb.query(tableName, null, "ReviewId = ?", new String[]{reviewId + ""}, null, null, null, null);
        Review r = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            r = new Review(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3)
            );
            cursor.close();
        }
        return r;
    }

}
