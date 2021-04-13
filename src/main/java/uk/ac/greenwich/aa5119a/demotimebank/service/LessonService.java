package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.dto.LessonDTO;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;
import uk.ac.greenwich.aa5119a.demotimebank.model.Lesson;
import uk.ac.greenwich.aa5119a.demotimebank.model.Subject;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.TeacherListing;
import uk.ac.greenwich.aa5119a.demotimebank.repository.LessonRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.SubjectRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.TeacherListingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    TeacherListingRepository teacherListingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubjectRepository subjectRepository;


    private ModelMapper modelMapper = new ModelMapper();


    public int getUserLessonCount(int userId) {
        return lessonRepository.getUserLessonCount(userId);
    }

    public int getUserTeachingLessonCount(int userId) {
        return lessonRepository.getUserTeachingLessonCount(userId);
    }


    public void createLesson(ClassBooking classBooking) {
//        Lesson lesson = new Lesson(classBooking.getClassId(), classBooking.getStudentId(), classBooking.getHours(), new Date(classBooking.getClassDate()), false, "Not Started", );

        Lesson lesson = new Lesson();
        lesson.setClassListingId(classBooking.getClassId());
        lesson.setStudentId(classBooking.getStudentId());
        lesson.setHours(classBooking.getHours());
//        lesson.setLessonDate(new Date(classBooking.getClassDate()));
        lesson.setLessonDate(Instant.ofEpochMilli(classBooking.getClassDate()).atZone(ZoneId.systemDefault()).toLocalDate());
        lesson.setStudentHasJoined(false);
        lesson.setTeacherHasJoined(false);
        lesson.setStatus("Not Started");
        lesson.setTimeLeft(classBooking.getHours() * 3600000);

        lessonRepository.save(lesson);

    }

    private LessonDTO convertToLessonDTO(Lesson lesson) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        LessonDTO lessonDTO = modelMapper.map(lesson, LessonDTO.class);

        TeacherListing teacherListing = teacherListingRepository.findById(lesson.getClassListingId()).get();
        User student = userRepository.findById(lesson.getStudentId()).get();
        User teacher = userRepository.findById(teacherListing.getUserId()).get();

        lessonDTO.setLessonPrice(lesson.getHours() * teacherListing.getTimeRate());
        lessonDTO.setStudentFirstName(student.getFirstname());
        lessonDTO.setStudentImage(student.getProfileImageUrl());

        lessonDTO.setTeacherFirstName(teacher.getFirstname());
        lessonDTO.setTeacherImage(teacher.getProfileImageUrl());
        lessonDTO.setTeacherRating(teacher.getRatingScore());

        Subject subject = subjectRepository.findById(teacherListing.getSubjectId()).get();
        lessonDTO.setSubjectTitle(subject.getTitle());
        lessonDTO.setSubjectIconUrl(subject.getIconUrl());


        return lessonDTO;
    }

    private List<LessonDTO> convertListToLessonْDTO(List<Lesson> lessons) {

        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDTOS.add(convertToLessonDTO(lesson));
        }

        return lessonDTOS;
    }


    public List<LessonDTO> getUserLessons(int userId) {
        List<Lesson> lessons = lessonRepository.getUserLessons(userId);

        return convertListToLessonْDTO(lessons);
    }

//    public List<Lesson> getUserLessons(int userId) {
//        List<Lesson> lessons = lessonRepository.getUserLessons(userId);
//
//        return lessons;
//    }

    public List<LessonDTO> getUserTeachingLessons(int userId) {
        List<Lesson> lessons = lessonRepository.getUserTeachingLessons(userId);

        return convertListToLessonْDTO(lessons);
    }

    public LessonDTO getLesson(int lessonId) {
        return convertToLessonDTO(lessonRepository.findById(lessonId).get());
    }

    public Boolean startLesson(int lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).get();
            lesson.setStatus("Started");
            lesson.setTeacherHasJoined(true);
            lessonRepository.save(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }


    public Boolean joinLesson(int lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).get();
            lesson.setStudentHasJoined(true);
            lessonRepository.save(lesson);


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public LessonDTO updateLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        return convertToLessonDTO(lessonRepository.save(convertToLesson(lessonDTO)));
    }

    public Boolean updateTimeLeft(int lessonId, long timeLeft) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).get();
            lesson.setTimeLeft(timeLeft);
            lessonRepository.save(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
//    public LessonDTO updateLesson(int lessonId, long timeLeft) {
//
//        Lesson lesson = lessonRepository.findById(lessonId).get();
//        lesson.setTimeLeft(timeLeft);
//
//        try {
//            lessonRepository.save(lesson);
//            return convertToLessonDTO(lesson);
//
//        } catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

    private Lesson convertToLesson(LessonDTO lessonDTO) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Lesson lesson = modelMapper.map(lessonDTO, Lesson.class);
        lesson.setLessonDate(lessonDTO.getLessonDate());
//
//        TeacherListing teacherListing = teacherListingRepository.findById(lesson.getClassListingId()).get();
//        User student = userRepository.findById(lesson.getStudentId()).get();
//        User teacher = userRepository.findById(teacherListing.getUserId()).get();
//
//        lessonDTO.setLessonPrice(lesson.getHours() * teacherListing.getTimeRate());
//        lessonDTO.setStudentFirstName(student.getFirstname());
//        lessonDTO.setStudentImage(student.getProfileImageUrl());
//
//        lessonDTO.setTeacherFirstName(teacher.getFirstname());
//        lessonDTO.setTeacherImage(teacher.getProfileImageUrl());
//        lessonDTO.setTeacherRating(teacher.getRatingScore());
//
//        Subject subject = subjectRepository.findById(teacherListing.getSubjectId()).get();
//        lessonDTO.setSubjectTitle(subject.getTitle());
//        lessonDTO.setSubjectIconUrl(subject.getIconUrl());


        return lesson;
    }


//    public void startTimer(int lessonId) {
//
//        Lesson lesson = lessonRepository.findById(lessonId).get();
//
//        long timeStarted = System.currentTimeMillis(); // started at 4:13pm
//
//        lesson.setTimeStarted(timeStarted);
//        lessonRepository.save(lesson);
//    }

    /*
     *
     *  1- lesson.getTimeLeft() initial = 600000 or 1 minute
     *
     *  2- runTimer();        System.currentTimeMillis() == 16353020054032  == 4:10pm
     *
     *  3- updateTimer();       System.currentTimeMillis() == 1645673254025  == 4:30pm
     *
     *      long timePassedMillis = timeStarted - currentTime
     *
     *        lesson.setTimeLeft(lesson.getTimeLeft() - timePassedMillis);
     *        lessonRepository.save(lesson)
     *
     *  4- pauseTimer();      System.currentTimeMillis() == 1645673254025  == 4:40pm
     *
     *
     *
     * */

    public HashMap<String, Object> runTimer(int lessonId, long timeStarted) {

        HashMap<String, Object> res = new HashMap<>();

        Boolean hasTimeLeft = false;
        Boolean hasStarted = true;

        Lesson lesson = lessonRepository.findById(lessonId).get();


//        long timePassedMillis = System.currentTimeMillis() - timeStarted;
        if (lesson.getTimeStarted() == -1) {
            lesson.setTimeStarted(System.currentTimeMillis());
        }

        long timePassedMillis = System.currentTimeMillis() - lesson.getTimeStarted();


        long timeLeft = lesson.getTimeLeft() - timePassedMillis;

        if (timeLeft > 0) {

            lesson.setTimeLeft(timeLeft);
            lesson.setTimeStarted(System.currentTimeMillis());

            lessonRepository.save(lesson);

            hasTimeLeft = true;

        } else {

            // lesson is over
            hasTimeLeft = false;
        }


        res.put("hasStarted", true);
        res.put("timeLeft", lesson.getTimeLeft());
        res.put("hasTimeLeft", hasTimeLeft);

        return res;
    }

    public HashMap<String, Object> updateLessonTimeRemaining(int lessonId) {

        HashMap<String, Object> res = new HashMap<>();

        Lesson lesson = lessonRepository.findById(lessonId).get();

        Boolean hasTimeLeft = false;

        long timePassedMillis = System.currentTimeMillis() - lesson.getTimeStarted();


        long timeLeft = lesson.getTimeLeft() - timePassedMillis;

        if (timeLeft > 0) {

            lesson.setTimeLeft(timeLeft);
            lesson.setTimeStarted(System.currentTimeMillis());

            lessonRepository.save(lesson);

            hasTimeLeft = true;

        } else {

            // lesson is over
            hasTimeLeft = false;
        }


        res.put("timeLeft", lesson.getTimeLeft());
        res.put("hasTimeLeft", hasTimeLeft);

        return res;



//        return lessonRepository.findById(lessonId).get().getTimeLeft();
//        HashMap<String, Object> res = new HashMap();
//
//        Lesson lesson = lessonRepository.findById(lessonId).get();
//
//
//        if(lesson.getTimeStarted() == -1){
//
//            res.put("hasStarted", false);
//            res.put("hasTimeLeft", true);
//            res.put("timeLeft", lesson.getTimeLeft());
//
//            return res;
//
//        } else {
//            return runTimer(lessonId, System.currentTimeMillis());
//        }
//
//        if (timeLeft > 0) {
//
//            lesson.setTimeLeft(timeLeft);
//            lesson.setTimeStarted(System.currentTimeMillis());
//
//            lessonRepository.save(lesson);
//
//            hasTimeLeft = true;
//
//        } else {
//
//            // lesson is over
//            hasTimeLeft = false;
//        }
//
//        res.put("timeLeft", lesson.getTimeLeft());
//        res.put("hasTimeLeft", hasTimeLeft);
//
//        return .getTimeLeft();
    }

    public Boolean startTimer(int lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).get();
            lesson.setTimeStarted(System.currentTimeMillis());

            lessonRepository.save(lesson);

            return true;
        } catch (Exception e) {
            return false;
        }


    }


//    public LessonDTO pauseTimer(int lessonId) {
//
//        long timeSincePause = System.currentTimeMillis(); // paused at 4:20pm
//
//        Lesson lesson = lessonRepository.findById(lessonId).get();
//
//
//    }
}
