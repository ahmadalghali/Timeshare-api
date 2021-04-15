package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson,  Integer> {

    @Query("SELECT COUNT(id) FROM lesson WHERE student_id = :userId AND has_finished = 0")
    int getUserLessonCount(int userId);

    @Query("SELECT COUNT(l.id) FROM lesson l \n" +
            "JOIN teacher_listing tl on  tl.id= l.class_listing_id\n" +
            "WHERE tl.teacher_id = :userId AND l.has_finished = 0")
    int getUserTeachingLessonCount(int userId);

    @Query("SELECT * FROM lesson WHERE student_id = :userId AND has_finished = 0")
    public List<Lesson> getUserLessons(int userId);

    @Query("SELECT l.* FROM lesson l \n" +
            "JOIN teacher_listing tl on  tl.id= l.class_listing_id\n" +
            "WHERE tl.teacher_id = :userId AND l.has_finished = 0")
    List<Lesson> getUserTeachingLessons(int userId);


}
