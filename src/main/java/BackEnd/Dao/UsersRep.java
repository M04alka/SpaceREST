package BackEnd.Dao;


import BackEnd.Model.Street;
import BackEnd.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRep extends JpaRepository<Users,Long>{
   Optional<Users> findUsersByFirstnameAndLastname(String firstName, String lastname);

}
