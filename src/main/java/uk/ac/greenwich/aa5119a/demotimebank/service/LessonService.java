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

    @Autowired
    UserService userService;


    private ModelMapper modelMapper = new ModelMapper();


    public int getUserLessonCount(int userId) {
        return lessonRepository.getUserLessonCount(userId);
    }

    public int getUserTeachingLessonCount(int userId) {
        return lessonRepository.getUserTeachingLessonCount(userId);
    }


    public void createLesson(ClassBooking classBooking) {
//        Lesson lesson = new Lesson(classBooking.getClassId(), classBooking.getStudentId(), classBooking.getHours(), new Date(classBooking.getClassDate()), false, "Not Started", );

        TeacherListing listing = teacherListingRepository.findById(classBooking.getClassId()).get();
        Lesson lesson = new Lesson();
        lesson.setClassListingId(classBooking.getClassId());
        lesson.setTeacherId(listing.getUserId());
        lesson.setStudentId(classBooking.getStudentId());
        lesson.setHours(classBooking.getHours());
//        lesson.setLessonDate(new Date(classBooking.getClassDate()));
        lesson.setLessonDate(Instant.ofEpochMilli(classBooking.getClassDate()).atZone(ZoneId.systemDefault()).toLocalDate());
        lesson.setStudentHasJoined(false);
        lesson.setTeacherHasJoined(false);
        lesson.setStatus("Not Started");
        lesson.setTimeLeft(classBooking.getHours() * 3600000);
        lesson.setTimeStarted(-1);
        lesson.setHasFinished(false);
        lesson.setStudentHasLeft(false);
        lesson.setTeacherHasLeft(false);





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

        lessonDTO.setTeacherEmail(teacher.getEmail());
        lessonDTO.setStudentEmail(student.getEmail());

        lessonDTO.setStudentPhoneNumber(student.getPhoneNumber());
        lessonDTO.setTeacherPhoneNumber(teacher.getPhoneNumber());

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

            if (userService.checkBalance(lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice())) {
                lesson.setStudentHasJoined(true);
                lessonRepository.save(lesson);

                return true;
            } else {
                return false;
            }


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

            lesson.setHasFinished(false);


            hasTimeLeft = true;

        } else {

            lesson.setHasFinished(true);

            // lesson is over
            hasTimeLeft = false;

        }

        lessonRepository.save(lesson);


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


    public Boolean endLesson(int lessonId, int userId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).get();

            if(lesson.getTeacherHasLeft() && lesson.getTimeLeft() > 10000){
                return true;
            }

            if(lesson.getStudentHasLeft() && lesson.getTimeStarted() == -1){
                return true;
            }

            TeacherListing classListing = teacherListingRepository.findById(lesson.getClassListingId()).get();

            int teacherId = classListing.getUserId();

            payTeacher(lesson, userId);

//            if (lesson.getStudentId() == userId) {
////                payTeacher(teacherId, lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
//                deductBalance(lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
//            } else {
//                addToBalance(userId, convertToLessonDTO(lesson).getLessonPrice());
//            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void payTeacher(Lesson lesson, int userId) {
        if (lesson.getStudentId() == userId) {
//                payTeacher(teacherId, lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
            deductBalance(lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
        } else {
            addToBalance(userId, convertToLessonDTO(lesson).getLessonPrice());
        }
    }

    private void addToBalance(int teacherId, double lessonPrice) {
        try {
            User teacher = userRepository.findById(teacherId).get();

            teacher.setTimeCreditsCount(teacher.getTimeCreditsCount() + lessonPrice);

            userRepository.save(teacher);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deductBalance(int studentId, double lessonPrice) {

        try {
            User student = userRepository.findById(studentId).get();

            student.setTimeCreditsCount(student.getTimeCreditsCount() - lessonPrice);

            userRepository.save(student);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean quitLesson(int lessonId, int userId) {

        Lesson lesson = lessonRepository.findById(lessonId).get();

        TeacherListing classListing = teacherListingRepository.findById(lesson.getClassListingId()).get();

        try {

            int teacherId = classListing.getUserId();

            boolean isStudent = lesson.getStudentId() == userId;

            if (isStudent) {
                if (lesson.getTimeStarted() != -1) {
//                    payTeacher(teacherId, lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
                    payTeacher(lesson, userId);

                }

                lesson.setStudentHasLeft(true);

            } else {
                //teacher quit

                boolean lessonHasStarted = lesson.getTimeStarted() != -1;
                if (lessonHasStarted && !lesson.getStudentHasJoined()) {
                    refundStudent(teacherId, lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
                }

                lesson.setTeacherHasLeft(true);
            }

            lesson.setHasFinished(true);

            lessonRepository.save(lesson);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



//    private void payTeacher(int teacherId, int studentId, double lessonPrice) {
//
//        try {
//            User student = userRepository.findById(studentId).get();
//            User teacher = userRepository.findById(teacherId).get();
//
//            student.setTimeCreditsCount(student.getTimeCreditsCount() - lessonPrice);
//            teacher.setTimeCreditsCount(teacher.getTimeCreditsCount() + lessonPrice);
//
//            userRepository.save(teacher);
//            userRepository.save(student);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



    private void refundStudent(int teacherId, int studentId, double lessonPrice) {


//        if (lesson.getStudentId() == userId) {
////                payTeacher(teacherId, lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
//            deductBalance(lesson.getStudentId(), convertToLessonDTO(lesson).getLessonPrice());
//        } else {
//            addToBalance(userId, convertToLessonDTO(lesson).getLessonPrice());
//        }
//        try {
//            User student = userRepository.findById(studentId).get();
//            User teacher = userRepository.findById(teacherId).get();
//
//            teacher.setTimeCreditsCount(teacher.getTimeCreditsCount() - lessonPrice);
//            student.setTimeCreditsCount(student.getTimeCreditsCount() + lessonPrice);
//
//            userRepository.save(teacher);
//            userRepository.save(student);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
