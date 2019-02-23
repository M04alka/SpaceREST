package BackEnd.Dao;

import BackEnd.Model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StreetInter extends JpaRepository<Street, Long> {
    Optional<Street> findStreetByNameAndCity_NameAndCity_Country_Name(String street_name, String City_Name, String City_Country_Name);
}
