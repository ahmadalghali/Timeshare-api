package uk.ac.greenwich.aa5119a.demotimebank.model.response;

import org.springframework.data.annotation.Id;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassBooking;

public class NotificationClassBookingResponse {

    private NotificationClassBooking notificationClassBooking;
    private User student;
    private User teacher;
    private ClassBooking classBooking;

    public NotificationClassBookingResponse(NotificationClassBooking notificationClassBooking, User student, User teacher, ClassBooking classBooking) {
        this.notificationClassBooking = notificationClassBooking;
        this.student = student;
        this.teacher = teacher;
        this.classBooking = classBooking;
    }

    public NotificationClassBooking getNotificationClassBooking() {
        return notificationClassBooking;
    }

    public User getStudent() {
        return student;
    }

    public User getTeacher() {
        return teacher;
    }

    public ClassBooking getClassBooking() {
        return classBooking;
    }
}
