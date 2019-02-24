package BackEnd.Dao;

import BackEnd.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepoz extends JpaRepository<Category,Long> {
    @Query("select name from Category ")
    List<String> getCategoriesName();
    Optional<String> findCategoriesByName(String nameCategory);
    Optional<String> deleteCategoryByName(String nameCategory);
}
