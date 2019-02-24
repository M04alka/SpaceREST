package BackEnd.Controller;

import BackEnd.Dao.ArticleRepoz;
import BackEnd.Dao.CategoryRepoz;
import BackEnd.Model.Article;
import BackEnd.Model.Category;
import BackEnd.Service.ArticleService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/articles")
public class Cont {
   @Autowired
    ArticleService articleService;
    @Autowired
    CategoryRepoz categoryRepoz;


    @GetMapping("/allCategory")
    public ResponseEntity<List<String>> kek() {
        return articleService.getAllCategories();
    }
    @GetMapping("/getArticle/{name}")
    public Object getArticles(@PathVariable("name") String name){
       return articleService.findArticlesByCategory_Name(name);
    }
    @GetMapping("/list/{name}/{count}")
    public ResponseEntity<List<Article>> getListOfArticals(@PathVariable @NotNull String name, @PathVariable @NotNull String count){
        return articleService.listOfArticle(name,count);
    }

    @GetMapping("/list/{count}")
    public ResponseEntity<List<Article>> getTopics(@PathVariable @NotNull String count){
        return articleService.getTopics(count);
    }
    @GetMapping("/listArticals/{name}")
    public ResponseEntity<List<Article>> getAllArticleByNameCatig(@PathVariable String name){
        return articleService.findArticlesByCategory_Name(name);
    }

    @GetMapping("/selectedArtical/{id}")
    public ResponseEntity<Article> getAllArticleById(@PathVariable String id){
        return articleService.findArticlesByTitle(id);
    }



}
