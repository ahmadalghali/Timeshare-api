package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassConfirmation;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.NotificationClassBookingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.NotificationClassConfirmationRequest;
import uk.ac.greenwich.aa5119a.demotimebank.repository.ClassBookingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.TeacherListingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationClassBookingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationClassConfirmationRepository;
//import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

//    @Autowired
//    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationClassBookingRepository notificationClassBookingRepository;

    @Autowired
    private NotificationClassConfirmationRepository notificationClassConfirmationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TeacherListingRepository teacherListingRepository;

    @Autowired
    ClassBookingRepository classBookingRepository;


    public List<Object> getUserNotifications(int receiverId) {

        List<Object> allUserNotifications = new ArrayList<>();

//        allUserNotifications.addAll(notificationRepository.findAllByReceiverIdOrderByIdIdDesc(receiverId));
        allUserNotifications.addAll(notificationClassBookingRepository.findAllByReceiverIdOrderByIdDesc(receiverId));
        allUserNotifications.addAll(notificationClassConfirmationRepository.findAllByReceiverIdOrderByIdDesc(receiverId));

        return allUserNotifications;

    }

//    public void createNotification(NotificationClassBookingRequest notificationRequest) {
//
//        ClassBooking classBooking = new ClassBooking(notificationRequest.getClassId(), notificationRequest.getSenderId(), false);
//        ClassBooking savedClassBooking = classBookingRepository.save(classBooking);
//
//
//        NotificationClassBooking notification = new NotificationClassBooking(
//                notificationRequest.getMessage(),
//                notificationRequest.getSenderId(),
//                notificationRequest.getReceiverId(),
//                userRepository.findById(notificationRequest.getSenderId()).get().getProfileImageUrl(),
//                savedClassBooking.getId()
//        );
//
//        notificationClassBookingRepository.save(notification);
//    }
//
//    public void createNotification(NotificationClassConfirmationRequest notificationRequest) {
//
//
//        NotificationClassConfirmation notification = new NotificationClassConfirmation(
//                notificationRequest.getMessage(),
//                notificationRequest.getSenderId(),
//                notificationRequest.getReceiverId(),
//                userRepository.findById(notificationRequest.getSenderId()).get().getProfileImageUrl(),
//                notificationRequest.getClassId()
//        );
//
//        notificationClassConfirmationRepository.save(notification);
//
//
//
//    }



    public NotificationClassBooking createNotification(NotificationClassBookingRequest notificationRequest) {

        ClassBooking classBooking = new ClassBooking(notificationRequest.getClassId(), notificationRequest.getSenderId(), false);
        ClassBooking savedClassBooking = classBookingRepository.save(classBooking);


        NotificationClassBooking notification = new NotificationClassBooking(
                notificationRequest.getMessage(),
                notificationRequest.getSenderId(),
                notificationRequest.getReceiverId(),
                userRepository.findById(notificationRequest.getSenderId()).get().getProfileImageUrl(),
                savedClassBooking.getId()
        );

        return notificationClassBookingRepository.save(notification);
    }



    public NotificationClassConfirmation createNotification(NotificationClassConfirmationRequest notificationRequest) {


        NotificationClassConfirmation notification = new NotificationClassConfirmation(
                notificationRequest.getMessage(),
                notificationRequest.getSenderId(),
                notificationRequest.getReceiverId(),
                userRepository.findById(notificationRequest.getSenderId()).get().getProfileImageUrl(),
                notificationRequest.getClassId()
        );

        return notificationClassConfirmationRepository.save(notification);
    }



    public void setClassBookingAccepted(int classBookingId, boolean isAccepted) {

        if(isAccepted){
            ClassBooking classBooking = classBookingRepository.findById(classBookingId).get();

            classBooking.setAccepted(true);
            classBookingRepository.save(classBooking);

            TeacherListing _class = teacherListingRepository.findById(classBooking.getClassId()).get();
            User teacher = userRepository.findById(_class.getUserId()).get();

            NotificationClassConfirmation notificationClassConfirmation =
                    new NotificationClassConfirmation("Class confirmed",
                            teacher.getId(),
                            classBooking.getStudentId(),
                            teacher.getProfileImageUrl(),
                            classBooking.getClassId()
                            );

            notificationClassConfirmationRepository.save(notificationClassConfirmation);

        }else{
            classBookingRepository.deleteById(classBookingId);
        }

    }
}
