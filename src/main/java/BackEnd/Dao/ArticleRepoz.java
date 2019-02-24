package BackEnd.Dao;

import BackEnd.Model.Article;
import BackEnd.Model.Category;
import BackEnd.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepoz extends JpaRepository<Article,Long> {
        Optional<List<Article>> findArticlesByCategory_Name(String name);
        Optional<Article> findArticlesById(Long id);
        Optional<String> deleteArticleByTitle(String title);
        Optional<Article> findArticlesByTitle(String title);
}
