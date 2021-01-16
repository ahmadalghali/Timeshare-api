package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
