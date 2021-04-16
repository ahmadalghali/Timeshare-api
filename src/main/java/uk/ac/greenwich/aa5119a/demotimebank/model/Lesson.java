package uk.ac.greenwich.aa5119a.demotimebank.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Lesson {

    @Id
    private int id;
    private int classListingId;
    private int studentId;
    private int hours;
    private LocalDate lessonDate;
    //    private Boolean isAttended;
    private Boolean teacherHasJoined;
    private Boolean studentHasJoined;
    private String status;
    private long timeLeft;

    private Boolean studentHasLeft;
    private Boolean teacherHasLeft;
    private int teacherId;

    private long timeStarted;

    private Boolean hasFinished;


    public Lesson(){

    }
    public Lesson(int classListingId, int studentId, int hours, LocalDate lessonDate, Boolean teacherHasJoined, Boolean studentHasJoined, String status, long timeLeft) {
        this.classListingId = classListingId;
        this.studentId = studentId;
        this.hours = hours;
        this.lessonDate = lessonDate;
        this.teacherHasJoined = teacherHasJoined;
        this.studentHasJoined = studentHasJoined;
        this.status = status;
        this.timeLeft = timeLeft;
    }


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
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

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }

    public Boolean getTeacherHasJoined() {
        return teacherHasJoined;
    }

    public void setTeacherHasJoined(Boolean teacherHasJoined) {
        this.teacherHasJoined = teacherHasJoined;
    }

    public Boolean getStudentHasJoined() {
        return studentHasJoined;
    }

    public void setStudentHasJoined(Boolean studentHasJoined) {
        this.studentHasJoined = studentHasJoined;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }




    //    public Boolean getAttended() {
//        return isAttended;
//    }
//
//    public void setAttended(Boolean attended) {
//        isAttended = attended;
//    }


    public long getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(long timeStarted) {
        this.timeStarted = timeStarted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getStudentHasLeft() {
        return studentHasLeft;
    }

    public void setStudentHasLeft(Boolean studentHasLeft) {
        this.studentHasLeft = studentHasLeft;
    }

    public Boolean getTeacherHasLeft() {
        return teacherHasLeft;
    }

    public void setTeacherHasLeft(Boolean teacherHasLeft) {
        this.teacherHasLeft = teacherHasLeft;
    }


}
