package uk.ac.greenwich.aa5119a.demotimebank.model.listing;

import org.springframework.data.annotation.Id;

public class Availability {

    @Id
    private int id;
    private String days;

    protected Availability() {
    }

    public Availability(String days) {
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}