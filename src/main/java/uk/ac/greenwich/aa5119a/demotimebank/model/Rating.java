package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;

public class Rating {

    @Id
    private int id;
    private int rating;
    private int userId;
    private String comments;


    public Rating(int rating, int userId, String comments) {
        this.rating = rating;
        this.userId = userId;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
