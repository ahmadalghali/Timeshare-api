package uk.ac.greenwich.aa5119a.demotimebank.web;

import uk.ac.greenwich.aa5119a.demotimebank.model.User;

public class RegisterResponse {
    private User user;
    private String message;

    public RegisterResponse() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
