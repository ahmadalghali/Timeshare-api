package uk.ac.greenwich.aa5119a.demotimebank.model.listing;

import org.springframework.data.annotation.Id;

public class TeachingStyle {

    @Id
    private int id;
    private String type;

    public TeachingStyle(String type) {
        this.type = type;
    }

    protected TeachingStyle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
