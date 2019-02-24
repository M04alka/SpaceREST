package BackEnd.Dao;

import java.util.Set;

import BackEnd.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Set<Roles> findAllByNameIn(Set<String> names);
}