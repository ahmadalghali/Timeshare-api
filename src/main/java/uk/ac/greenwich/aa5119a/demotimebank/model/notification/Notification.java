package uk.ac.greenwich.aa5119a.demotimebank.model.notification;



public class Notification {

    private int type;
    private Object notification;

    public Notification(int type, Object notification) {
        this.type = type;
        this.notification = notification;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getNotification() {
        return notification;
    }

    public void setNotification(Object notification) {
        this.notification = notification;
    }



//    @Id
//    private int id;
//    private String message;
//    private int senderId;
//    private int receiverId;
//    private int type;
//
//    public Notification(String message, int senderId, int receiverId, int type) {
//        this.message = message;
//        this.senderId = senderId;
//        this.receiverId = receiverId;
//        this.type = type;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(int senderId) {
//        this.senderId = senderId;
//    }
//
//    public int getReceiverId() {
//        return receiverId;
//    }
//
//    public void setReceiverId(int receiverId) {
//        this.receiverId = receiverId;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
}
