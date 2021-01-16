package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
