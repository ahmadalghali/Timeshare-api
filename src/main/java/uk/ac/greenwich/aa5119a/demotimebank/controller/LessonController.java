package uk.ac.greenwich.aa5119a.demotimebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.greenwich.aa5119a.demotimebank.dto.LessonDTO;
import uk.ac.greenwich.aa5119a.demotimebank.service.LessonService;

import java.util.HashMap;
import java.util.List;

@RestController
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("account/lessonCount")
    public int getUserLessonCount(@RequestParam() int userId) {
        return lessonService.getUserLessonCount(userId);
    }


    @GetMapping("account/teachingLessonCount")
    public int getUserTeachingLessonCount(@RequestParam() int userId) {
        return lessonService.getUserTeachingLessonCount(userId);
    }

    @GetMapping("lessons/{userId}")
    public List<LessonDTO> getUserLessons(@PathVariable("userId") int userId) {
        return lessonService.getUserLessons(userId);
    }

//    @PutMapping("lesson/update")
//    public LessonDTO updateLesson(@RequestBody LessonDTO lesson){
//        return lessonService.updateLesson(lesson);
//    }

//    @PutMapping("lesson/update")
//    public LessonDTO updateLesson(@RequestParam("lessonId") int lessonId,
//                                    @RequestParam("timeLeft") long timeLeft
//                                    ){
//        return lessonService.updateLesson(lessonId, timeLeft);
//    }

    @PutMapping("lesson/updateTimeLeft")
    public Boolean updateTimeLeft(@RequestParam("lessonId") int lessonId,
                               @RequestParam("timeLeft") long timeLeft) {
       return lessonService.updateTimeLeft(lessonId, timeLeft);
    }


    @GetMapping("lesson/{lessonId}")
    public LessonDTO getLesson(@PathVariable("lessonId") int lessonId) {
        return lessonService.getLesson(lessonId);
    }

    @GetMapping("lessons/teach/{userId}")
    public List<LessonDTO> getUserTeachingLessons(@PathVariable("userId") int userId) {
        return lessonService.getUserTeachingLessons(userId);
    }

    @PutMapping("lesson/{lessonId}/startClass")
    public Boolean startLesson(@PathVariable("lessonId") int lessonId) {
        return lessonService.startLesson(lessonId);
    }

    @PutMapping("lesson/{lessonId}/joinLesson")
    public Boolean joinLesson(@PathVariable("lessonId") int lessonId) {
        return lessonService.joinLesson(lessonId);
    }


    @PutMapping("lesson/runTimer")
    public HashMap<String, Object> runTimer(@RequestParam int lessonId, @RequestParam long timeStarted){
        return lessonService.runTimer(lessonId, timeStarted);
    }

    @PostMapping("lesson/startTimer")
    public Boolean startTimer(@RequestParam int lessonId){
        return lessonService.startTimer(lessonId);
    }

    @GetMapping("lesson/timerRemaining")
    public HashMap<String, Object> getLessonTimeRemaining(@RequestParam int lessonId){
        return lessonService.updateLessonTimeRemaining(lessonId);
    }


}
