package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.Lesson;

import java.util.HashMap;
import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson,  Integer> {

    @Query("SELECT COUNT(id) FROM lesson WHERE student_id = :userId")
    int getUserLessonCount(int userId);

    @Query("SELECT * FROM lesson WHERE student_id = :userId")
    public List<Lesson> getUserLessons(int userId);
}
