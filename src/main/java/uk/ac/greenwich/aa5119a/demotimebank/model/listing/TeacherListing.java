package uk.ac.greenwich.aa5119a.demotimebank.model.listing;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TeacherListing {


    @Id
    private int id;
    private int subjectId;
    @Column("teacher_id")
    private int userId;
    private String title;
    private String description;
    private String imageId;
    private Double timeRate;

    private Set<AvailabilityRef> availabilityIds = new HashSet<>();
    private Set<TeachingStyleRef> teachingStyleIds = new HashSet<>();



    public TeacherListing(){

    }

    public TeacherListing(int subjectId, int userId, String title, String description, String imageId, Double timeRate) {
        this.subjectId = subjectId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.timeRate = timeRate;
    }


    public void addAvailability(Availability availability){
        this.availabilityIds.add(new AvailabilityRef(availability.getId()));
    }
    public void addTeachingStyle(TeachingStyle teachingStyle){
        this.teachingStyleIds.add(new TeachingStyleRef(teachingStyle.getId()));
    }


    public Set<Integer> getAvailabilityIds(){
        return this.availabilityIds.stream().map(AvailabilityRef::getAvailabilityId).collect(Collectors.toSet());
    }

    public Set<Integer> getTeachingStyleIds(){
        return this.teachingStyleIds.stream().map(TeachingStyleRef::getTeachingStyleId).collect(Collectors.toSet());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Double getTimeRate() {
        return timeRate;
    }

    public void setTimeRate(Double timeRate) {
        this.timeRate = timeRate;
    }

    @Override
    public String toString() {
        return "TeacherListing{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId='" + imageId + '\'' +
                ", timeRate=" + timeRate +
                ", availabilityIds=" + availabilityIds +
                ", teachingStyleIds=" + teachingStyleIds +
                '}';
    }
}
