package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;

public class Category {

    @Id
    private int id;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
