package BackEnd.Service;

import BackEnd.DTO.ArticlesDto;
import BackEnd.Dao.ArticleRepoz;
import BackEnd.Dao.CategoryRepoz;
import BackEnd.Model.Article;
import BackEnd.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@PreAuthorize("hasRole('ADMIN')")
public class AdminService {
    @Autowired
    CategoryRepoz categoryRepoz;
    @Autowired
    ArticleRepoz articleRepoz;
    public ResponseEntity addNewCategory(@NotNull String nameCategory){
        if(categoryRepoz.findCategoriesByName(nameCategory).isPresent()){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        Category category = new Category();
        category.setName(nameCategory);
        categoryRepoz.save(category);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity removeCategory(@NotNull String nameCategory){
        if(categoryRepoz.findCategoriesByName(nameCategory).isPresent()){
            categoryRepoz.deleteCategoryByName(nameCategory);
            return new ResponseEntity(HttpStatus.OK);
        }
       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> removeArticle(@NotNull String title){
        if(articleRepoz.findArticlesByTitle(title).isPresent()){
            articleRepoz.deleteArticleByTitle(title);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    public ResponseEntity<String> addArticle(@NotNull ArticlesDto articlesDto){
       if(articleRepoz.findArticlesByTitle(articlesDto.getTitle()).isPresent()) {
           return new ResponseEntity<String>(HttpStatus.CONFLICT);
       }
        Article article = new Article(articlesDto.getContent(),articlesDto.getTitle(),String.valueOf(System.currentTimeMillis()),articlesDto.getPictureUrl());
        article.setCategory(categoryRepoz.getOne(articlesDto.getCategory_id()));
        articleRepoz.save(article);
       return new ResponseEntity<String>(HttpStatus.CREATED);
    }

}
