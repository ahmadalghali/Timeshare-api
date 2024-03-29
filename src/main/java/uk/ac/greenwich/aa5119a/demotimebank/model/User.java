package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("_user")
public class User {

    @Id
    private int id;
    private String email;
    private String password;
    private String city;
    private String firstname;
    @Column("profile_image_url")
    private String profileImageUrl;
    private double ratingScore;
    private int ratingCount;
    private double timeCreditsCount;
    private String phoneNumber;
    private int loginCount;


    protected User() {
    }

    public User(String email, String password, String city, String profileImageUrl, double ratingScore, int ratingCount) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.profileImageUrl = profileImageUrl;
        this.ratingScore = ratingScore;
        this.ratingCount = ratingCount;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getTimeCreditsCount() {
        return timeCreditsCount;
    }

    public void setTimeCreditsCount(double timeCreditsCount) {
        this.timeCreditsCount = timeCreditsCount;
    }


    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
