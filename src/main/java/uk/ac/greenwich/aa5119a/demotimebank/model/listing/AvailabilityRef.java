package uk.ac.greenwich.aa5119a.demotimebank.model.listing;


import org.springframework.data.relational.core.mapping.Table;

@Table("teacher_listing_availability")
public class AvailabilityRef {
    private int availabilityId;

    public AvailabilityRef(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }
}
