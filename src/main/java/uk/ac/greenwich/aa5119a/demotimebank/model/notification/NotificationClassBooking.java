package uk.ac.greenwich.aa5119a.demotimebank.model.notification;

import org.springframework.data.annotation.Id;

public class NotificationClassBooking {

    @Id
    private int id;
    private String message;
    private int senderId;
    private int receiverId;
    private String senderProfileImageUrl;
    private int classBookingId;


    public NotificationClassBooking(String message, int senderId, int receiverId, String senderProfileImageUrl, int classBookingId) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderProfileImageUrl = senderProfileImageUrl;
        this.classBookingId = classBookingId;
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

    public String getSenderProfileImageUrl() {
        return senderProfileImageUrl;
    }

    public void setSenderProfileImageUrl(String senderProfileImageUrl) {
        this.senderProfileImageUrl = senderProfileImageUrl;
    }

    public int getClassBookingId() {
        return classBookingId;
    }

    public void setClassBookingId(int classBookingId) {
        this.classBookingId = classBookingId;
    }
}
