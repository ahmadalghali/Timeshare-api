package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;

public class ClassBooking {

    @Id
    private int id;
    private int classId;
    private int studentId;
    private boolean isAccepted;
    private int hours;
    private long classDate;

    public ClassBooking(int classId, int studentId, boolean isAccepted, int hours, long classDate) {
        this.classId = classId;
        this.studentId = studentId;
        this.isAccepted = isAccepted;
        this.hours = hours;
        this.classDate = classDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public long getClassDate() {
        return classDate;
    }

    public void setClassDate(long classDate) {
        this.classDate = classDate;
    }
}
