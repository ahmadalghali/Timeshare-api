package uk.ac.greenwich.aa5119a.demotimebank.model.request;


import java.util.List;

public class TeacherListingRequest {


    private int subjectId;
    private int userId;
    private String title;
    private String description;
    private String qualificationImageUrl;
    private Double timeRate;

    private List<Integer> availabilityIds;
    private List<Integer> teachingStyleIds;


    public TeacherListingRequest(int subjectId, int userId, String title, String description, String qualificationImageUrl, Double timeRate, List<Integer> availabilityIds, List<Integer> teachingStyleIds) {
        this.subjectId = subjectId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.qualificationImageUrl = qualificationImageUrl;
        this.timeRate = timeRate;
        this.availabilityIds = availabilityIds;
        this.teachingStyleIds = teachingStyleIds;
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

    public String getQualificationImageUrl() {
        return qualificationImageUrl;
    }

    public Double getTimeRate() {
        return timeRate;
    }

    public void setTimeRate(Double timeRate) {
        this.timeRate = timeRate;
    }

    public void setQualificationImageUrl(String qualificationImageUrl) {
        this.qualificationImageUrl = qualificationImageUrl;
    }

    public List<Integer> getAvailabilityIds() {
        return availabilityIds;
    }

    public void setAvailabilityIds(List<Integer> availabilityIds) {
        this.availabilityIds = availabilityIds;
    }

    public List<Integer> getTeachingStyleIds() {
        return teachingStyleIds;
    }

    public void setTeachingStyleIds(List<Integer> teachingStyleIds) {
        this.teachingStyleIds = teachingStyleIds;
    }

    @Override
    public String toString() {
        return "TeacherListingRequest{" +
                ", subjectId=" + subjectId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId='" + qualificationImageUrl + '\'' +
                ", timeRate=" + timeRate +
                ", availabilityIds=" + availabilityIds +
                ", teachingStyleIds=" + teachingStyleIds +
                '}';
    }
}
