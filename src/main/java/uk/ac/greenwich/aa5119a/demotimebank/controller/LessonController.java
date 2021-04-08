package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.greenwich.aa5119a.demotimebank.dto.LessonDTO;
import uk.ac.greenwich.aa5119a.demotimebank.model.Lesson;
import uk.ac.greenwich.aa5119a.demotimebank.service.LessonService;

import java.util.HashMap;
import java.util.List;

@RestController
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("account/lessonCount")
    public int getUserLessonCount(@RequestParam() int userId){
        return lessonService.getUserLessonCount(userId);
    }

    @GetMapping("lessons/{userId}")
    public List<LessonDTO> getUserLessons(@PathVariable("userId") int userId){
        return lessonService.getUserLessons(userId);
    }
}
