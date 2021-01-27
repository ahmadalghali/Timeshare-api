package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.Subject;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.Notification;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassConfirmation;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.NotificationClassBookingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.NotificationClassConfirmationRequest;
import uk.ac.greenwich.aa5119a.demotimebank.repository.ClassBookingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.SubjectRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.TeacherListingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationClassBookingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationClassConfirmationRepository;

import java.util.ArrayList;
import java.util.List;

//import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationRepository;

@Service
public class NotificationService {



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

    @Autowired
    SubjectRepository subjectRepository;






    public List<Notification> getUserNotifications(int userId) {

        List<Notification> allUserNotifications = new ArrayList<>();

        List<ClassBooking> receivedClassRequests = classBookingRepository.findAllByTeacherId(userId);


        List<ClassBooking> acceptedClassBookings = classBookingRepository.findAllByStudentIdAndAcceptedIsTrue(userId);

        allUserNotifications.addAll(getClassBookingNotifications(receivedClassRequests));
        allUserNotifications.addAll(getClassConfirmedNotifications(acceptedClassBookings));


        return allUserNotifications;

    }


    private List<Notification> getClassConfirmedNotifications(List<ClassBooking> acceptedClassBookings) {

        List<NotificationClassConfirmation> notificationClassConfirmationList = new ArrayList<>();

        for (ClassBooking acceptedBooking : acceptedClassBookings) {

            TeacherListing _class = teacherListingRepository.findById(acceptedBooking.getClassId()).get();
//            User student = userRepository.findById(request.getStudentId()).get();
            User teacher = userRepository.findById(teacherListingRepository.findById(acceptedBooking.getClassId()).get().getUserId()).get();

            Subject subject = subjectRepository.findById(_class.getSubjectId()).get();
            String subjectTitle = subject.getTitle();
            String subjectIconUrl = subject.getIconUrl();

            String teacherName = teacher.getFirstname();
            String teacherProfileImage = teacher.getProfileImageUrl();


            notificationClassConfirmationList.add(new NotificationClassConfirmation(subjectTitle, teacherName,teacherProfileImage ,subjectIconUrl , acceptedBooking.getClassDate(), acceptedBooking.getClassId()));
        }

        List<Notification> notifications = new ArrayList<>();

        for (NotificationClassConfirmation notificationClassConfirmation : notificationClassConfirmationList) {
            notifications.add(new Notification(3, notificationClassConfirmation));
        }

        return notifications;
    }


    private List<Notification> getClassBookingNotifications(List<ClassBooking> receivedClassRequests) {

        List<NotificationClassBooking> notificationClassBookings = new ArrayList<>();

        for (ClassBooking request : receivedClassRequests) {

            TeacherListing _class = teacherListingRepository.findById(request.getClassId()).get();
            User student = userRepository.findById(request.getStudentId()).get();
//            User teacher = userRepository.findById(teacherListingRepository.findById(request.getClassId()).get().getUserId()).get();

            Subject subject = subjectRepository.findById(_class.getSubjectId()).get();
            String subjectTitle = subject.getTitle();
            String subjectIconUrl = subject.getIconUrl();

            int hours = request.getHours();
            double timeCreditPrice = hours * _class.getTimeRate();
            String studentName = student.getFirstname();
            String studentProfileImage = student.getProfileImageUrl();


            notificationClassBookings.add(new NotificationClassBooking(subjectTitle, hours, timeCreditPrice, studentName, studentProfileImage, subjectIconUrl, request));
        }

        List<Notification> notifications = new ArrayList<>();

        for (NotificationClassBooking notificationClassBooking : notificationClassBookings) {
            notifications.add(new Notification(2, notificationClassBooking));
        }

        return notifications;
    }



    public void setClassBookingAccepted(int classBookingId, boolean isAccepted) {

        if (isAccepted) {
            ClassBooking classBooking = classBookingRepository.findById(classBookingId).get();

            classBooking.setAccepted(true);
            classBookingRepository.save(classBooking);

            TeacherListing _class = teacherListingRepository.findById(classBooking.getClassId()).get();
            User teacher = userRepository.findById(_class.getUserId()).get();



//            notificationClassConfirmationRepository.save(notificationClassConfirmation);

        } else {
            classBookingRepository.deleteById(classBookingId);
        }

    }
}
