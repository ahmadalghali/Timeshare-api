package uk.ac.greenwich.aa5119a.demotimebank.model.response;

import uk.ac.greenwich.aa5119a.demotimebank.model.User;

public class LoginResponse {

    private User user;
    private String message;

    public LoginResponse() {

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
