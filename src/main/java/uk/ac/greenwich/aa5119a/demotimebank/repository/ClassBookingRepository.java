package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.ClassBooking;

@Repository
public interface ClassBookingRepository extends CrudRepository<ClassBooking, Integer> {

}
