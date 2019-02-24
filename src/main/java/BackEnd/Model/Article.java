package BackEnd.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String content;

    private String title;

    private String timeStamp;

    private String urlPicture;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Override
    public String toString() {
        return "id=" + id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", timeStamp=" + timeStamp +
                ", urlPicture='" + urlPicture + '\'';
    }

    public Article(@NotNull String content, String title, String timeStamp, String urlPicture) {
        this.content = content;
        this.title = title;
        this.timeStamp = timeStamp;
        this.urlPicture = urlPicture;
    }
}
