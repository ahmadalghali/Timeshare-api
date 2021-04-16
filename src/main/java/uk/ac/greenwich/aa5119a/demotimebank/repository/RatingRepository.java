package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {


    @Query("select * from rating where user_id = :userId")
    List<Rating> findAllByUserId(int userId);

    @Query("select count(id) from rating where user_id = :id")
    int getUserRatingCount(int id);

    @Query("select SUM(rating) from rating where user_id = :id")
    int getUserRatingSum(int id);
}
