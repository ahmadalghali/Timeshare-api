package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class User {

    @Id
    private int id;
    private String email;
    private String password;
    private String city;
    @Column("profile_image_url")
    private String profileImageUrl;

    protected User() {
    }

    public User(String email, String password, String city, String profileImageUrl) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.profileImageUrl = profileImageUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
