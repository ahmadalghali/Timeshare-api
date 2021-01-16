package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.TeacherListingResponse;
import uk.ac.greenwich.aa5119a.demotimebank.service.TeacherListingService;

@RestController
public class TeacherListingController {


    @Autowired
    private TeacherListingService teacherListingService;


    @PostMapping("teach/listing")
    public TeacherListingResponse addListing(@RequestBody TeacherListing teacherListing){
        return teacherListingService.addListing(teacherListing);
    }
}
