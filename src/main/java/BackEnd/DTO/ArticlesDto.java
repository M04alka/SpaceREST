package BackEnd.DTO;

import BackEnd.Model.Article;
import lombok.Data;

@Data
public class ArticlesDto  {
    String title;
    String content;
    String pictureUrl;
    Long category_id;
}
