package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.Subject;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.ClassBookingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.TeacherListingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.TeacherListingResponse;
import uk.ac.greenwich.aa5119a.demotimebank.repository.*;
import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationClassBookingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.notification.NotificationClassConfirmationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademyService {


    @Autowired
    private TeacherListingRepository teacherListingRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private TeachingStyleRepository teachingStyleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassBookingRepository classBookingRepository;


    @Autowired
    private NotificationClassBookingRepository notificationClassBookingRepository;

    @Autowired
    private NotificationClassConfirmationRepository notificationClassConfirmationRepository;


    @Autowired
    UserService userService;



    public TeacherListingResponse addListing(TeacherListingRequest listingRequest) {

        TeacherListingResponse teacherListingResponse = new TeacherListingResponse();

        try {

//
            TeacherListing teacherListing = new TeacherListing(
                    listingRequest.getSubjectId(),
                    listingRequest.getUserId(),
                    listingRequest.getTitle(),
                    listingRequest.getDescription(),
                    listingRequest.getQualificationImageUrl(),
                    listingRequest.getTimeRate()
            );

//            teacherListing.setPhoneNumber(teacherListing.getPhoneNumber());

//            if(listingRequest.getAvailabilityIds().size() > 0){
            for (int availabilityId : listingRequest.getAvailabilityIds()) {
                teacherListing.addAvailability(availabilityRepository.findById(availabilityId).get());
            }
//            }


//            if(listingRequest.getTeachingStyleIds().size() > 0){
            for (int styleId : listingRequest.getTeachingStyleIds()) {
                teacherListing.addTeachingStyle(teachingStyleRepository.findById(styleId).get());
            }
//            }


            TeacherListing savedDBListing = teacherListingRepository.save(teacherListing);


            teacherListingResponse.setMessage("added");

            teacherListingResponse.setTeacherListing(savedDBListing);

            teacherListingResponse.setUser(userRepository.findById(savedDBListing.getUserId()).get());

        } catch (Exception e) {
            teacherListingResponse.setMessage("failed to save in database");
            teacherListingResponse.setTeacherListing(null);
            e.printStackTrace();
        }

        return teacherListingResponse;
    }


    public List<TeacherListingResponse> getClassesBySubject(int subjectId) {

        List<TeacherListing> listings = teacherListingRepository.findAllBySubjectId(subjectId);

        List<TeacherListingResponse> responses = new ArrayList<>();


        for (TeacherListing listing : listings) {

            TeacherListingResponse response = new TeacherListingResponse();
            Subject subject = subjectRepository.findById(listing.getSubjectId()).get();
            User user = userRepository.findById(listing.getUserId()).get();


            response.setSubject(subject);
            response.setUser(user);
            response.setTeacherListing(listing);
//            response.setMessage("");

            responses.add(response);
        }

        return responses;
    }


    public Boolean bookClass(ClassBookingRequest classBookingRequest) {

        TeacherListing listing = teacherListingRepository.findById(classBookingRequest.getClassId()).get();

        double lessonPrice = listing.getTimeRate() * classBookingRequest.getHours();

        if(classBookingRequest.getStudentId() == listing.getUserId()){
            return false;
        }

        if(userService.checkBalance(classBookingRequest.getStudentId(),lessonPrice)){
            classBookingRepository.save(new ClassBooking(classBookingRequest.getClassId(), classBookingRequest.getStudentId(), classBookingRequest.isAccepted(), classBookingRequest.getHours(), classBookingRequest.getClassDate()));

            return true;
        }else {
            return false;
        }

    }

    public Boolean isClassRequestedByUser(int classId, int studentId) {

//        ClassBooking booking = classBookingRepository.isClassRequestedByUser(classId, studentId);

        return  classBookingRepository.isClassRequestedByUser(classId, studentId) > 0;

    }


}
