package uk.ac.greenwich.aa5119a.demotimebank.model.response;

import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;

public class TeacherListingResponse {

    private TeacherListing teacherListing;

    private String message;

    public TeacherListingResponse() {
    }

    public TeacherListingResponse(TeacherListing teacherListing, String message) {
        this.teacherListing = teacherListing;
        this.message = message;
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
