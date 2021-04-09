package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Lesson lesson = new Lesson(classBooking.getClassId(), classBooking.getStudentId(), classBooking.getHours(), new Date(classBooking.getClassDate()), false, "Not Started");
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

    public List<LessonDTO> getUserTeachingLessons(int userId) {
        List<Lesson> lessons = lessonRepository.getUserTeachingLessons(userId);

        return convertListToLessonْDTO(lessons);
    }

    public LessonDTO getLesson(int lessonId) {
        return convertToLessonDTO(lessonRepository.findById(lessonId).get());
    }
}
