package BackEnd.Service;

import BackEnd.Dao.ArticleRepoz;
import BackEnd.Dao.CategoryRepoz;
import BackEnd.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    ArticleRepoz articleRepoz;
    @Autowired
    CategoryRepoz categoryRepoz;

    public ResponseEntity<List<Article>> listOfArticle(@NotNull String name, @NotNull String limit) {
        try {
            Integer.valueOf(limit);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<List<Article>> articleServices = articleRepoz.findArticlesByCategory_Name(name);
        if (articleServices.isPresent()) {
            List<Article> listResult = articleServices.get().stream().filter(element -> element.getId() >= articleServices.get().size() - Integer.valueOf(limit)).collect(Collectors.toList());
            return new ResponseEntity<>(listResult, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Article>> getTopics(String limit) {
        List <Article> articles = articleRepoz.findAll();
        if(articles.size() >= Integer.valueOf(limit))
        {
        List<Article> resul = new ArrayList<>();
        if(articles.size()>=Integer.valueOf(limit)) {
            for (int i = articles.size() - Integer.valueOf(limit); i < articles.size(); i++) {
                resul.add(articles.get(i));
            }
            return new ResponseEntity<>(resul, HttpStatus.OK);
        }}
        else return new  ResponseEntity<List<Article>>(articles, HttpStatus.OK);
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    public ResponseEntity<List<Article>> findArticlesByCategory_Name(@NotNull String name) {
        Optional<List<Article>> articles = articleRepoz.findArticlesByCategory_Name(name);
        if (articles.isPresent()) {
            List<Article> listResult = new ArrayList<>(articles.get());
            return new ResponseEntity<List<Article>>(listResult, HttpStatus.OK);
        }
        return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Article> findArticlesByTitle(@NotNull String id) {
        try{
            Optional<Article> selectedArticle = articleRepoz.findArticlesById(Long.valueOf(id));
            return selectedArticle.map(article -> new ResponseEntity<>(article, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            return new ResponseEntity<Article>(HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity<List<String>> getAllCategories(){
        try{
           return new  ResponseEntity<>(categoryRepoz.getCategoriesName(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}


