package uk.ac.greenwich.aa5119a.demotimebank.model.listing;

import org.springframework.data.relational.core.mapping.Table;

@Table("teacher_listing_teaching_style")
public class TeachingStyleRef {
    private int teachingStyleId;

    public TeachingStyleRef(int teachingStyleId) {
        this.teachingStyleId = teachingStyleId;
    }

    public int getTeachingStyleId() {
        return teachingStyleId;
    }
}
