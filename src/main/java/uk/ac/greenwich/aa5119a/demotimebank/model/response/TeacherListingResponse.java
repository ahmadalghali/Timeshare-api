package uk.ac.greenwich.aa5119a.demotimebank.model.response;

import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;

public class TeacherListingResponse {

    private TeacherListing teacherListing;

    private User user;

    private String message;

    public TeacherListingResponse() {
    }

    public TeacherListingResponse(TeacherListing teacherListing, User user, String message) {
        this.teacherListing = teacherListing;
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TeacherListing getTeacherListing() {
        return teacherListing;
    }

    public void setTeacherListing(TeacherListing teacherListing) {
        this.teacherListing = teacherListing;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
