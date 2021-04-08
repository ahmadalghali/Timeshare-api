package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;

public class Lesson {

    @Id
    private int id;
    private int classListingId;
    private int studentId;
    private int hours;
    private long lessonDate;
    private Boolean isAttended;
    private String status;

    public Lesson(int classListingId, int studentId, int hours, long lessonDate, Boolean isAttended, String status) {
        this.classListingId = classListingId;
        this.studentId = studentId;
        this.hours = hours;
        this.lessonDate = lessonDate;
        this.isAttended = isAttended;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassListingId() {
        return classListingId;
    }

    public void setClassListingId(int classListingId) {
        this.classListingId = classListingId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public long getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(long lessonDate) {
        this.lessonDate = lessonDate;
    }


    public Boolean getAttended() {
        return isAttended;
    }

    public void setAttended(Boolean attended) {
        isAttended = attended;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
