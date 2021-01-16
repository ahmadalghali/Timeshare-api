package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.repository.CrudRepository;
import uk.ac.greenwich.aa5119a.demotimebank.model.listing.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability,Integer> {
}
