package com.thetrendybazaar.thetrendybazaar;

public class Review {
    Integer id;
    Integer articleId;
    Integer rating;
    String detailedReview;


    public Review(Integer id, Integer articleId, Integer rating, String detailedReview){
        this.id = id;
        this.articleId = articleId;
        this.rating = rating;
        this.detailedReview = detailedReview;
    }
    @Override
    public String toString() {
        return "id = " + id + "articleid = " + articleId;
    }
}
