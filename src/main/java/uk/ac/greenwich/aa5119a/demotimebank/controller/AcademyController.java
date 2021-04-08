package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.ClassBookingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.TeacherListingRequest;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.BookClassResponse;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.TeacherListingResponse;
import uk.ac.greenwich.aa5119a.demotimebank.service.AcademyService;

import java.util.List;

@RestController
public class AcademyController {


    @Autowired
    private AcademyService academyService;


    @PostMapping("teach/listing")
    public TeacherListingResponse addListing(@RequestBody TeacherListingRequest listingRequest){
        return academyService.addListing(listingRequest);
    }


    @GetMapping("learn/subject/{subjectId}")
    public List<TeacherListingResponse> getClassesBySubject(@PathVariable("subjectId") int subjectId){
        return academyService.getClassesBySubject(subjectId);
    }


    @PostMapping("book/class")
    public Boolean bookClass(@RequestBody ClassBookingRequest classBookingRequest){

        return academyService.bookClass(classBookingRequest);
    }

    @GetMapping("class/{classId}/isRequestedBy/user/{studentId}")
    public Boolean isClassRequestedByUser(@PathVariable("classId") int classId, @PathVariable("studentId") int studentId){

        return academyService.isClassRequestedByUser(classId, studentId);
    }


}
