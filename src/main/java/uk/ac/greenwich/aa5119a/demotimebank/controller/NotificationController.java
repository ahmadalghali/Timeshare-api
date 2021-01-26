package uk.ac.greenwich.aa5119a.demotimebank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.Notification;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassConfirmation;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.NotificationClassBookingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.NotificationClassConfirmationRequest;
import uk.ac.greenwich.aa5119a.demotimebank.service.NotificationService;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


//    @PostMapping("notifications/class_booking_request")
//    public void createNotification(@RequestBody NotificationClassBookingRequest notification){
//        notificationService.createNotification(notification);
//    }
//
//    @PostMapping("notifications/class_confirmation")
//    public void createNotification(@RequestBody NotificationClassConfirmationRequest notification){
//        notificationService.createNotification(notification);
//    }

//    @MessageMapping("notifications/class_booking_request")
//    @SendTo("topic/notifications")
//    public NotificationClassBooking createNotification(@RequestBody NotificationClassBookingRequest notification){
//        return notificationService.createNotification(notification);
//    }
//
//    @MessageMapping("notifications/class_confirmation")
//    @SendTo("topic/notifications")
//    public NotificationClassConfirmation createNotification(@RequestBody NotificationClassConfirmationRequest notification){
//        return notificationService.createNotification(notification);
//    }




    @GetMapping("notifications/{userId}")
    public List<Notification> getUserNotifications(@PathVariable("userId") int userId){
        return notificationService.getUserNotifications(userId);
    }



    @PutMapping("class_booking/{classBookingId}/isAccepted={isAccepted}")
    public void setClassBookingAccepted(@PathVariable("classBookingId") int classBookingId,@PathVariable("isAccepted") boolean isAccepted ){
        notificationService.setClassBookingAccepted(classBookingId, isAccepted);
    }


}
