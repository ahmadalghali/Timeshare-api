package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.Subject;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.Availability;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeachingStyle;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.TeacherListingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.TeacherListingResponse;
import uk.ac.greenwich.aa5119a.demotimebank.repository.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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


    public TeacherListingResponse addListing(TeacherListingRequest listingRequest) {

        TeacherListingResponse teacherListingResponse = new TeacherListingResponse();

        try{
//
//            Availability availability = new Availability("Weekends");
//            Availability availability2 = new Availability("Weekdays");
//            TeachingStyle style1 = new TeachingStyle("Online");
//
//
//            TeacherListing teacherListing1 = new TeacherListing(1,141,"A Level Maths", "blah blah", "imagekey12345");
//
//            teacherListing.addAvailability(availability);
//            teacherListing.addAvailability(availability2);
//            teacherListing.addTeachingStyle(style1);
//
            TeacherListing teacherListing = new TeacherListing(
                    listingRequest.getSubjectId(),
                    listingRequest.getUserId(),
                    listingRequest.getTitle(),
                    listingRequest.getDescription(),
                    listingRequest.getImageId(),
                    listingRequest.getTimeRate()
                    );

//            if(listingRequest.getAvailabilityIds().size() > 0){
                for(int availabilityId : listingRequest.getAvailabilityIds()){
                    teacherListing.addAvailability(availabilityRepository.findById(availabilityId).get());
                }
//            }


//            if(listingRequest.getTeachingStyleIds().size() > 0){
                for(int styleId : listingRequest.getTeachingStyleIds()){
                    teacherListing.addTeachingStyle(teachingStyleRepository.findById(styleId).get());
                }
//            }


            TeacherListing savedDBListing = teacherListingRepository.save(teacherListing);


            teacherListingResponse.setMessage("added");

            teacherListingResponse.setTeacherListing(savedDBListing);

            teacherListingResponse.setUser(userRepository.findById(savedDBListing.getUserId()).get());

        }catch (Exception e){
            teacherListingResponse.setMessage("failed to save in database");
            teacherListingResponse.setTeacherListing(null);
            e.printStackTrace();
        }

        return teacherListingResponse;
    }



    public List<TeacherListingResponse> getClassesBySubject(int subjectId) {

        List<TeacherListing> listings = teacherListingRepository.findAllBySubjectId(subjectId);

        List<TeacherListingResponse> responses = new ArrayList<>();


        for(TeacherListing listing : listings){

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
}
