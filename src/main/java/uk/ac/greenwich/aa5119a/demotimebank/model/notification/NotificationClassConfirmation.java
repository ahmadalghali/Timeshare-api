package uk.ac.greenwich.aa5119a.demotimebank.model.notification;

import org.springframework.data.annotation.Id;

public class NotificationClassConfirmation {


    @Id
    private int id;
    private String message;
    private int senderId;
    private int receiverId;
    private String teacherProfileImageUrl;
    private int classId;

    public NotificationClassConfirmation(String message, int senderId, int receiverId, String teacherProfileImageUrl, int classId) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.teacherProfileImageUrl = teacherProfileImageUrl;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getTeacherProfileImageUrl() {
        return teacherProfileImageUrl;
    }

    public void setTeacherProfileImageUrl(String teacherProfileImageUrl) {
        this.teacherProfileImageUrl = teacherProfileImageUrl;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
