package BackEnd.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "city",cascade=CascadeType.ALL)
    private List<Street> streets;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
