package uk.ac.greenwich.aa5119a.demotimebank.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);


    @Query(value = "SELECT password FROM user WHERE email = :email")
    String findPasswordByEmail(@Param("email") String email);


}
