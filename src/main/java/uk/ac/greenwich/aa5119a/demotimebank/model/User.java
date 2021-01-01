package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private int id;
    private String email;
    private String password;
//    private boolean loggedIn;

    protected User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
//        this.loggedIn = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public boolean isLoggedIn() {
//        return loggedIn;
//    }
//
//    public void setLoggedIn(boolean loggedIn) {
//        this.loggedIn = loggedIn;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
