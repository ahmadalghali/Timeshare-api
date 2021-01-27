package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;

import java.util.List;

@Repository
public interface ClassBookingRepository extends CrudRepository<ClassBooking, Integer> {

    @Query("SELECT cb.id, cb.class_id, cb.student_id,cb.is_accepted,cb.hours, cb.class_date " +
            "FROM class_booking cb JOIN teacher_listing class ON class.id = cb.class_id JOIN user u ON u.id = class.teacher_id" +
            " WHERE class.teacher_id = :teacherId")
    List<ClassBooking> findAllByTeacherId(@Param("teacherId") int teacherId);



    @Query("select * from class_booking where student_id = :studentId and is_accepted = true")
    List<ClassBooking> findAllByStudentIdAndAcceptedIsTrue(int studentId);


    @Query("SELECT COUNT(cb.id) FROM class_booking cb WHERE cb.class_id = :classId AND cb.student_id = :studentId")
    int isClassRequestedByUser(@Param("classId") int classId, @Param("studentId") int studentId);
}
