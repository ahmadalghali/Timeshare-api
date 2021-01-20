package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {


    List<Rating> findAllByUserId(int userId);
}
