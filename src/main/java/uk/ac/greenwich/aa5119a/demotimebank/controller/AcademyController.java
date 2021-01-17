package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.request.TeacherListingRequest;
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
    public List<TeacherListing> getClassesBySubject(@PathVariable("subjectId") int subjectId){
        return academyService.getClassesBySubject(subjectId);
    }
}
