package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.Subject;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.Availability;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeachingStyle;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.TeacherListingResponse;
import uk.ac.greenwich.aa5119a.demotimebank.repository.TeacherListingRepository;

@Service
public class TeacherListingService {


    @Autowired
    private TeacherListingRepository teacherListingRepository;

    public TeacherListingResponse addListing(TeacherListing teacherListing) {

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


            TeacherListing dbListing = teacherListingRepository.save(teacherListing);


            teacherListingResponse.setMessage("added");

            teacherListingResponse.setTeacherListing(dbListing);

        }catch (Exception e){
            teacherListingResponse.setMessage("failed to save in database");
            teacherListingResponse.setTeacherListing(teacherListing);

        }

        return teacherListingResponse;
    }
}
